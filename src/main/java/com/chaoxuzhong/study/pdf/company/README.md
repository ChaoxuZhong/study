### 亚丁1号牌开户PDF
#### 一、准备开户PDF模板
1.使用Adobe Acrobat Pro或其它工具将PDF模板填充完毕

2.开户PDF枚举FieldEnums实现类一一映射PDF中指定的域名



#### 二、添加模板步骤
1.将模板pdf copy 到 src/main/resources/pdf/template

2.FileVoName 中配置需要加载的模板，加载1中配置的模板，可一对多配置，一个PDF文件由多个模板生成

3.SimplePdfDaoFactory 中配置填充模板需要的数据层handler，可配置多个

4.PdfCompanyFieldNameDaoConfig 中配置模板PDF域所需数据。配置的map中，key对应pdf域名称，value pdf域填充数据的方式：包含数据来源key，数据类型（复选框、文本等）。


#### 三、可测试性保证
