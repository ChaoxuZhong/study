//package com.chaoxuzhong.study.drools;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.*;
//
//public class LoadRule {
//    private Logger log = LoggerFactory.getLogger(LoadRule.class);
//
//1
//    /**
//     * 加载规则
//     */
//    public void loadRule() {
//        try {
//            List<RuleDTO> ruleDTOs = getActivityRuleList();
//            log.info("{}条加入规则引擎", ruleDTOs.size());
//            if (!ruleDTOs.isEmpty()) {
//                RuleGenerator generator = new RuleGenerator();
//                generator.generateRules(ruleDTOs);
//            }
//        } catch (Exception e) {
//            log.error("RuleService.loadRule。e={}", e.getMessage(), e);
//        }
//    }
//
//    /**
//     * 从数据库里面取规则
//     */
//    public List<RuleDTO> getActivityRuleList() {
//        Date begin = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
//        Date end = Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant());
//
//        List<ActivityRule> list = testService.selectAll();
//        List<RuleDTO> ruleDTOList = new ArrayList<>();
//        for (ActivityRule dto : list) {
//            RuleDTO ruleDTO = new RuleDTO();
//            ruleDTO.setBeginTime(begin);
//            ruleDTO.setEndTime(end);
//            ruleDTO.setRule(dto);
//            ruleDTOList.add(ruleDTO);
//        }
//        return ruleDTOList;
//    }
//
//    /**
//     * 根据传递进来的参数对象生规则 * * @param ruleDTOs
//     */
//    public void generateRules(List<RuleDTO> ruleDTOs) {
//        List<String> ruleDrls = new ArrayList<>();
//        for (int i = 0; i < ruleDTOs.size(); i++) {        //规则的生成        String drlString = applyRuleTemplate(ruleDTOs.get(i));        ruleDrls.add(drlString);        log.info("规则引擎加载规则,id-{}", ruleDTOs.get(i).getRule().getId());    }    //规则的加载    createOrRefreshDrlInMemory(ruleDrls);}
//
///**     * 根据Rule生成drl的String     */
//private String applyRuleTemplate (RuleDTO ruleDTO){
//                Map<String, Object> data = prepareData(ruleDTO);//        log.info("rule={}", JSON.toJSON(ruleDTO));        ObjectDataCompiler objectDataCompiler = new ObjectDataCompiler();        return objectDataCompiler.compile(Arrays.asList(data), Thread.currentThread().getContextClassLoader().getResourceAsStream("give-reward-rule-template.drt"));    }
//                /**     * 根据Rule生成drl的map data     */protected Map<String, Object> prepareData (RuleDTO ruleDTO){
//                    Map<String, Object> data = new HashMap<>();
//                    ActivityRule rule = ruleDTO.getRule();
//                    data.put("ruleCode", ruleDTO.hashCode());
//                    data.put("beginTime", DateUtil.dateToStringFormat(ruleDTO.getBeginTime(), "dd-MMM-yyyy"));
//                    data.put("endTime", DateUtil.dateToStringFormat(ruleDTO.getEndTime(), "dd-MMM-yyyy"));
//                    data.put("eventType", FactManager.getFactClassByEvent(rule.getEvent()).getName());
//                    data.put("rule", rule.getRuleValue());
//                    data.put("awardeeType", rule.getAwardeeType());//        data.put("ruleId", rule.getId());//        data.put("joinChannels", ruleDTO.getJoinChannel());//        data.put("priority", rule.getPriority());//        log.info("data={}", JSON.toJSON(data));        return data;    }
//
///** * 根据String格式的Drl生成Maven结构的规则 * * @param rules */
//private void createOrRefreshDrlInMemory (List < String > rules) {
//                        KieServices kieServices = KieServices.Factory.get();
//                        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
//                        kieFileSystem.generateAndWritePomXML(RuleExecutor.getReleaseId());
//                        for (String str : rules) {
//                            kieFileSystem.write("src/main/resources/" + UUID.randomUUID() + ".drl", str);
//                            log.info("str={}", str);
//                        }
//                        KieBuilder kb = kieServices.newKieBuilder(kieFileSystem).buildAll();
//                        if (kb.getResults().hasMessages(Message.Level.ERROR)) {
//                            log.error("create rule in kieFileSystem Error", kb.getResults());
//                            throw new IllegalArgumentException("生成规则文件失败");
//                        }
//                        doAfterGenerate(kieServices);
//                    }
//                }
