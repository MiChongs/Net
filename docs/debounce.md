现在应用的搜索输入框一般情况下都是输入完搜索关键词后自动发起请求开始搜索

这个过程涉及到以下需求:

1. 不能每次变化都开始搜索请求, 这样会导致多余的网络资源浪费. 所以应该在用户停止输入后的指定时间后(默认800毫秒)开始搜索
2. 当产生新的搜索请求后取消旧的请求以防止旧数据覆盖新数据
3. 当输入内容没有变化(例如复制粘贴重复内容到搜索框)不会发起搜索请求

<br>

截图预览

<img src="https://i.imgur.com/encjFdc.gif" width="250"/>

<br>

```kotlin
var scope: CoroutineScope? = null

et_input.debounce().listen(this) {
    scope?.cancel() // 发起新的请求前取消旧的请求, 避免旧数据覆盖新数据
    scope = scopeNetLife { // 保存旧的请求到一个变量中
        tv_request_content.text = "请求中"
        val data = Get<String>("http://api.k780.com/?app=life.time&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json",
                               absolutePath = true).await()
        tv_request_content.text = JSONObject(data).getJSONObject("result").getString("datetime_2")
    }
}
```

如果想要设置自己的节流阀超时时间请指定参数
```kotlin
fun EditText.debounce(timeoutMillis: Long = 800)
```