### 1、线程池的使用

```java

  //根据技能id获取数据 单个技能id
ExecutorService pool = new ThreadPoolExecutor(6, 20, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
CompletionService<Map> cService = new ExecutorCompletionService<Map>(pool);
for (String skillId : skillIds) {
	List<String> finalSkillIds = skillIds;//添加环境name
	List<String> finalSkillNames = skillNames;
	cService.submit(new Callable<Map>() {
		@Override
		public Map call() throws Exception {
			Map<String,Object> tempMap = new HashMap<>();
			tempMap = getOneTwoTaskZTData(taskId, new String[]{skillId}, tenantCode, provCode);
			tempMap.put("skillId",skillId);
			tempMap.put("skillName", finalSkillNames.get(finalSkillIds.indexOf(skillId)) == null ? "" :  finalSkillNames.get(finalSkillIds.indexOf(skillId)));
			tempMap.put("taskId",taskId);
			return tempMap;
		}
	});
}
//获取每个任务查询的结果
List<Map<String,Object>> tempList = new ArrayList<>();
try {
	for (String s : skillIds) {
		Future<Map> take = cService.take();
		if (take != null) {
			Map map = take.get();
			tempList.add(map);//放入templist中 方便后边排序
		}
	}
}catch (Exception e){
	LOGGER.error("二级任务图标数据查询失败");
}

//排序
for (String sk: skillIds) {
	for (Map<String, Object> map : tempList) {
		if (sk.equals(String.valueOf(map.get("skillId")))){
			resultList.add(map);
			break;
		}
	}
}

pool.shutdown();

```
