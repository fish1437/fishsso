# fishsso
基于spring boot 独立开发的一套统一认证解决方案，易读、易拓展

集中统一认证核心在拦截器实现

当前版本支持from表单提交拦截和ajax请求拦截

校验规则为从前端请求头中获取accesstoken，校验其有效性

核心类说明：

SecurityInterceptor（拦截器实现）

SecurityMemoryConstant（启动加载ip白名单、路由白名单）

SecurityRunner（启动加载白名单校验）

WebSecurityConfig（配置路由规则）

需要配置如下：

1.redis缓存配置见RedisUtils


需要二次开发内容：

开发登录、登录接口

可以完善内容：

token有效期设置

redis服务化

白名单过滤


完成以上步骤，你将拥有了一个自己的oss！


更多内容请加群或者添加微信fish1773，提供免费技术支持
群名称：
fishsso
群   号：
836728388


