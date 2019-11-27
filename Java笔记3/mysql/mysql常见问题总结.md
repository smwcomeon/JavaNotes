### 解决mysql字段大小写不敏感问题
```xml
  select * from t_ucp_servicetype where servicetypeid  binary regexp '^[A-Z]'
