# MeiqiaSDK-Push-Signature-Example
美洽 SDK 3.0 推送的数据结构签名算法，多语言示例。


该项目是 `美洽 SDK 3.0` 推送的数据结构签名的算法，具体推送的数据结构请移步 [美洽 SDK 推送说明](https://app-s3-cdn.b0.upaiyun.com/pics.meiqia.bucket/images/9e5ef170-79a9-4765-80ae-430fa9b834f1.png)。

开发者收到美洽的推送数据后，开发者可以选择进行 `签名验证`。

我们使用的签名算法是 [HMAC-SHA1](https://en.wikipedia.org/wiki/Hash-based_message_authentication_code)，key 使用的是美洽 App 的 `Secret-Key`，请到工作台获取你的 `Secret-Key`。**Warning**，请不要将 `Secret-Key` 暴露给任何人。

下图是获取 `Secret-Key` 截图：
![获取 Secret-Key 截图](https://s3.cn-north-1.amazonaws.com.cn/pics.meiqia.bucket/038ccdc55dafe225)

开发者收到美洽的推送数据请求后，请获取头信息的 `Authorization`，其中是美洽推送数据的签名。

我们提供 `Java、Python、Ruby、JavaScript、PHP、Golang` 五种语言的计算签名的代码，计算签名后，请和头信息的美洽数据签名进行比对，如果比对失败，则说明推送数据被修改过。

# 如何使用

### 数据样例

假如你填写的 URL 是 http://your.push.com/meiqia, 那么当有推送消息需要发送到你的服务器时，会有如下网络请求

```http
POST /meiqia HTTP/1.1
Host: your.push.com
Authorization: meiqia_sign:YTgyMmFkMDY5OWJjYzI1ODRkNjBhNzAyMTdlY2M4YjU2NDNiYjhkZA==
Content-Type: application/json

{"clientId":"12vc86d41d1611e69e6f029346effd31","content":"test","contentType":"text","customizedData":{"name":"#4","avatar":"https://app.meiqia.com/api/static/client-avatar/11-01.png"},"deviceOS":"Android","deviceToken":"d2433d6ad2861515e24316ddcbdg05eea23d","fromName":"美洽","messageId":"111","messageTime":"2016-01-01T00:00:00.622580"}
```

### 比对

```json
{"clientId":"12vc86d41d1611e69e6f029346effd31","content":"test","contentType":"text","customizedData":{"name":"#4","avatar":"https://app.meiqia.com/api/static/client-avatar/11-01.png"},"deviceOS":"Android","deviceToken":"d2433d6ad2861515e24316ddcbdg05eea23d","fromName":"美洽","messageId":"111","messageTime":"2016-01-01T00:00:00.622580"}
```

如上数据，我们称之为 http-body。

sign 在网络请求的头部中的 `Authorization`

```http
meiqia_sign:YTgyMmFkMDY5OWJjYzI1ODRkNjBhNzAyMTdlY2M4YjU2NDNiYjhkZA==
```

用 `Secret-Key` 对 http-body 进行  [HMAC-SHA1](https://en.wikipedia.org/wiki/Hash-based_message_authentication_code) 操作，然后就会得到 sign。

将两个 sign 进行比对，如果一样，就说明该请求合法，可以进行其他操作，如果不对，就舍弃。

### 自测

开发者的回调接口开发完成后，请先自己发送一条消息，测试一下回调接口是否能正确接收数据，如发送：

```
curl -X POST  -H "Accept: Application/json" -H "Content-Type: application/json" '你的回调 api 的地址' -d '{"content": "Hi, stranger!"}'
```

或发送 mock-message:

```
curl -X POST  -H "Accept: Application/json" -H "Content-Type: application/json" '你的回调 api 的地址' -d '{"clientId":"1jk23u3i434jkdjkf","content":"test","contentType":"text","customizedData":{"name":"#4","avatar":"https://app.meiqia.com/api/static/client-avatar/11-01.png"},"deviceOS":"Android","deviceToken":"d2433d6ad2861515e24316ddcbdg05eea23d","fromName":"美洽","messageId":"111","messageTime":"2016-01-01T00:00:00.622580"}'
```

## 目录说明

```html
.
├── Golang       Golang 的 sign 代码及测试
├── Java         Java 的 sign 代码及测试
├── LICENSE      许可证
├── Node.js      Node.js 的 sign 代码及测试
├── PHP          PHP 的 sign 代码及测试
├── Python       Python 的 sign 代码及测试
├── README.md  
├── Ruby         Ruby 的 sign 代码及测试
└── sample.txt   确保 sign 正确的一些测试样例，与推送数据格式无关
```




#Thanks
感谢 [@yangwenmai](https://github.com/yangwenmai) 提供了 go 语言版的代码样例。
