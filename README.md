# SeetaFace2Android
To make SeetaFace2 easy to use on Android.

### Details
#### Screenshot
|  Face Detection | Blur |
| ------------ | -------------- |
|![pic](https://github.com/hzy3774/SeetaFace2Android/blob/master/misc/demo1.png?raw=true)|![pic](https://github.com/hzy3774/SeetaFace2Android/blob/master/misc/demo2.png?raw=true)|

#### Instructions
* SeetaFace2 人脸检测使用方便，模型文件也不是很大，比较适合移动端使用
* 该工程只是将其对接Bitmap接口以方便安卓设备实际使用，更多扩展和功能请参考
  [SeetaFace2](https://github.com/seetafaceengine/SeetaFace2)
* for more information: https://github.com/seetafaceengine/SeetaFace2

#### 下载Demo体验
下载Demo体验[下载地址](https://github.com/huzongyao/SeetaFace2Android/releases/latest)

#### Dependencies
``` gradle
// face detect only(模型文件1.65MB)
implementation 'com.hzy.face:seeta2:1.0.0'

// face detect and 81 landmarks(模型文件4.5MB)
implementation 'com.hzy.face:seeta2mark:1.0.0'
```

#### Easy Java Api
``` java
// init
Seeta2Detector.INSTANCE.init(context);

// detect
Seeta2Detector.INSTANCE.detect(bitmap);

// landmarks
Seeta2Detector.INSTANCE.mark81(bitmap);

// destory
Seeta2Detector.INSTANCE.destory();
```

### About Me
 * GitHub: [https://huzongyao.github.io/](https://huzongyao.github.io/)
 * ITEye博客：[https://hzy3774.iteye.com/](https://hzy3774.iteye.com/)
 * 新浪微博: [https://weibo.com/hzy3774](https://weibo.com/hzy3774)

### Contact To Me
 * QQ: [377406997](https://wpa.qq.com/msgrd?v=3&uin=377406997&site=qq&menu=yes)
 * Gmail: [hzy3774@gmail.com](mailto:hzy3774@gmail.com)
 * Foxmail: [hzy3774@qq.com](mailto:hzy3774@qq.com)
 * WeChat: hzy3774

 ![image](https://raw.githubusercontent.com/hzy3774/AndroidP7zip/master/misc/wechat.png)

### Others
 * 想捐助我喝杯热水(¥0.01起捐)</br>
 ![donate](https://github.com/huzongyao/JChineseChess/blob/master/misc/donate.png?raw=true)