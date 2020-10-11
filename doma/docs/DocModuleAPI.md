# 文档模块接口文档

## 文档的创建

- **描述**：根据用户id+文档名进行文档记录的创建（包括了文件上传业务）

- **地址**：http://localhost:8080/createdoc

- **请求方式**：POST

- **参数**：

  | 字段            | 类型    | 说明           | 必需           |
  | --------------- | ------- | -------------- | -------------- |
  | ```userId```    | String  | 用户ID         | 是             |
  | ```docName```   | String  | 文档名         | 是             |
  | ```file```      | file    | 文件           | 是             |
  | ```recommend``` | Integer | 是否推荐，1或0 | 否，默认为0    |
  | ```style```     | String  | 分类           | 否，默认为null |
  | ```existann```  | Integer | 是否注解       | 否，默认为0    |
  | ```content```   | String  | 注解内容       | 否，默认为null |

- **返回值(暂时)：**

  | 状态     | 返回格式 |
  | -------- | -------- |
  | 上传成功 | ```"msg" : "上传成功！太棒了JOJO！``` |
  | 上传失败 | ```"msg" : "文件上传失败，服务器有问题"``` |
  | 文件为空导致的上传失败 | ```"msg" : "文件为空，上传失败"``` |
  | 用户id不存在导致的上传失败 | ```"msg" : "用户不存在"``` |

- **ajax示例：**

  - 见```templates\test\CreateDoc.html```

  ```javascript
  //从表单中获取文件生成FormData对象(表单中第一项)
  var formData = new FormData();
  var userId = $("#userId").val();
  var docName = $("#docName").val();
  //将用户id与文档id加入formData中
  formData.append("file",document.getElementById("file").files[0]);
  formData.append("userId",userId);
  formData.append("docName",docName);
  $.ajax({
      type:"post",
      url:"http://localhost:8080/createdoc",
      async:true,
      data:formData,
      /**
                       *必须false才会自动加上正确的Content-Type
                       */
      contentType: false,
      /**
                       * 必须false才会避开jQuery对 formdata 的默认处理
                       * XMLHttpRequest会对 formdata 进行正确的处理
                       */
      processData: false,
      success:function (data){
          //看看客户端回传的数据
          console.log(data);
      }
  ```

  


----------

## 文档文件的下载

- **描述**：根据文档id进行下载，暂时不用ajax

- **地址**：http://localhost:8080/download

- **请求方式**：GET

- **参数**：

  | 字段        | 类型   | 说明   | 必需 |
  | ----------- | ------ | ------ | ---- |
  | ```docId``` | String | 文档ID | 是   |

- **返回值**：文件作为附件下载

