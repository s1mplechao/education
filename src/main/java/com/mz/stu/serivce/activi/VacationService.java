package com.mz.stu.serivce.activi;


import com.mz.stu.entity.VacTask;
import com.mz.stu.entity.Vacation;
import com.mz.stu.query.BaseQuery;
import com.mz.stu.util.ActivitiUtil;
import com.mz.stu.util.PageList;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


@Service
public class VacationService {

    @Resource
    private RuntimeService runtimeService;
    @Resource
    private IdentityService identityService;
    @Resource
    private TaskService taskService;
    @Resource
    private HistoryService historyService;


   private static final String PROCESS_DEFINE_KEY = "studentProcess";

   //开始请假流程
    public Object startVac(String userName, Vacation vac) {

        identityService.setAuthenticatedUserId(userName);
        // 开始流程
        ProcessInstance vacationInstance = runtimeService.startProcessInstanceByKey(PROCESS_DEFINE_KEY);
        // 查询当前任务
        Task currentTask = taskService.createTaskQuery().processInstanceId(vacationInstance.getId()).singleResult();
        // 声明任务
        taskService.claim(currentTask.getId(), userName);

        Map<String, Object> vars = new HashMap<>(4);
        vars.put("applyUser", userName);
        vars.put("days", vac.getDays());
        vars.put("reason", vac.getReason());

        taskService.complete(currentTask.getId(), vars);

        return true;
    }

    //学生申请的记录
    public PageList myVac(String userName,BaseQuery baseQuery) {
        List<ProcessInstance> instanceList = runtimeService.createProcessInstanceQuery()
                .startedBy(userName).listPage(baseQuery.getOffset(),baseQuery.getPageSize());
        List<Vacation> vacList = new ArrayList<>();
        for (ProcessInstance instance : instanceList) {
            Vacation vac = getVac(instance);
            vacList.add(vac);
        }
        PageList pageList = new PageList();
        int size = runtimeService.createProcessInstanceQuery()
                .startedBy(userName).list().size();
        pageList.setTotal(Long.valueOf(size));
        pageList.setRows(vacList);
        return pageList;
    }
    //得到请假信息
    private Vacation getVac(ProcessInstance instance) {
        Integer days = runtimeService.getVariable(instance.getId(), "days", Integer.class);
        String reason = runtimeService.getVariable(instance.getId(), "reason", String.class);
        Vacation vac = new Vacation();
        vac.setApplyUser(instance.getStartUserId());
        if(days !=null) {
            vac.setDays(days);
        }
        vac.setReason(reason);
        Date startTime = instance.getStartTime(); // activiti 6 才有
        vac.setApplyTime(startTime);
        vac.setApplyStatus(instance.isEnded() ? "申请结束" : "等待审批");
        return vac;
    }

    //待审核的记录 分页查询
    public PageList myAudit(String userName,BaseQuery baseQuery) {
        List<Task> taskList = taskService.createTaskQuery().orderByTaskCreateTime().desc().taskCandidateUser(userName)
                .listPage(baseQuery.getOffset(),baseQuery.getPageSize())

                ;

        Long total = Long.valueOf(taskService.createTaskQuery().taskCandidateUser(userName)
                .list().size());

        List<VacTask> vacTaskList = new ArrayList<>();
        for (Task task : taskList) {
            VacTask vacTask = new VacTask();
            vacTask.setId(task.getId());
            vacTask.setName(task.getName());
            vacTask.setCreateTime(task.getCreateTime());
            String instanceId = task.getProcessInstanceId();
            ProcessInstance instance = runtimeService.createProcessInstanceQuery().processInstanceId(instanceId).singleResult();
            Vacation vac = getVac(instance);
            vacTask.setVac(vac);
            vacTaskList.add(vacTask);
        }
        PageList pageList = new PageList();
        pageList.setTotal(total);
        pageList.setRows(vacTaskList);
        return pageList;
    }

    public Object passAudit(String userName, VacTask vacTask) {
        String taskId = vacTask.getId();
        String result = vacTask.getVac().getResult();
        Map<String, Object> vars = new HashMap<>();
        vars.put("result", result);
        vars.put("auditor", userName);
        vars.put("auditTime", new Date());
        taskService.claim(taskId, userName);
        taskService.complete(taskId, vars);
        return true;
    }

    //我申请过的记录
    public PageList myVacRecord(String userName, BaseQuery baseQuery) {
        List<HistoricProcessInstance> hisProInstance = historyService.createHistoricProcessInstanceQuery()
                .processDefinitionKey(PROCESS_DEFINE_KEY).startedBy(userName).finished()
                .listPage(baseQuery.getOffset(),baseQuery.getPageSize());

        int total = historyService.createHistoricProcessInstanceQuery()
                .processDefinitionKey(PROCESS_DEFINE_KEY).startedBy(userName).finished().list().size();

        List<Vacation> vacList = new ArrayList<>();
        for (HistoricProcessInstance hisInstance : hisProInstance) {
            Vacation vacation = new Vacation();
            vacation.setApplyUser(hisInstance.getStartUserId());
            vacation.setApplyTime(hisInstance.getStartTime());
            vacation.setApplyStatus("申请结束");
            List<HistoricVariableInstance> varInstanceList = historyService.createHistoricVariableInstanceQuery()
                    .processInstanceId(hisInstance.getId()).list();
            ActivitiUtil.setVars(vacation, varInstanceList);
            vacList.add(vacation);
        }
        PageList pageList = new PageList();
        pageList.setRows(vacList);
        pageList.setTotal(Long.valueOf(total));
        return pageList;
    }

    //我的审核记录
    public PageList myAuditRecord(String userName,BaseQuery baseQuery) {
        List<HistoricProcessInstance> hisProInstance = historyService.createHistoricProcessInstanceQuery()
                .processDefinitionKey(PROCESS_DEFINE_KEY).involvedUser(userName).finished()
                .listPage(baseQuery.getOffset(),baseQuery.getPageSize());

        int total = historyService.createHistoricProcessInstanceQuery()
                .processDefinitionKey(PROCESS_DEFINE_KEY).involvedUser(userName).finished()
                .list().size();

        List<String> auditTaskNameList = new ArrayList<>();
        auditTaskNameList.add("老师审核");
        List<Vacation> vacList = new ArrayList<>();
        PageList pageList = new PageList();
        for (HistoricProcessInstance hisInstance : hisProInstance) {
            List<HistoricTaskInstance> hisTaskInstanceList = historyService.createHistoricTaskInstanceQuery()
                    .processInstanceId(hisInstance.getId()).processFinished()
                    .taskAssignee(userName)
                    .taskNameIn(auditTaskNameList)
                    .orderByHistoricTaskInstanceEndTime().desc().list();
            boolean isMyAudit = false;
            for (HistoricTaskInstance taskInstance : hisTaskInstanceList) {
                if (taskInstance.getAssignee().equals(userName)) {
                    isMyAudit = true;
                }
            }
            if (!isMyAudit) {
                continue;
            }
            Vacation vacation = new Vacation();
            vacation.setApplyUser(hisInstance.getStartUserId());
            vacation.setApplyStatus("申请结束");
            vacation.setApplyTime(hisInstance.getStartTime());
            List<HistoricVariableInstance> varInstanceList = historyService.createHistoricVariableInstanceQuery()
                    .processInstanceId(hisInstance.getId()).list();
            ActivitiUtil.setVars(vacation, varInstanceList);
            vacList.add(vacation);


        }


        pageList.setTotal(Long.valueOf(total));
        pageList.setRows(vacList);
        return pageList;
    }
}
