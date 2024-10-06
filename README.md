# AutoThemeView
通过在xml中配置日间、夜间模式的颜色样式，view可以判断日间、夜间模式自动展示不同的样式

#### 项目演示
|日间、夜间模式切换|切换模式后返回上一页|
|:---:|:---:|
|![](https://github.com/user-attachments/assets/85ef77d0-41b1-4734-a7c0-b8d3052f22c0)|![](https://github.com/user-attachments/assets/76d61f32-d26f-40c9-9bec-6da38e039589)|
|点击水波纹效果||
|![飞书20241006-212949](https://github.com/user-attachments/assets/5b7df1c6-b189-4fa7-949e-b4a82155204e)||

## 简单用例
#### 1.在 build.gradle 中添加依赖
```gradle
com.github.codercool:AutoThemeView:1.0.11
```
#### 2.在 Application 中初始化AutoThemeManager
```kotlin
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // 设置默认参数
        val defaultParams = AutoThemeManager.Builder(this)
            /*.setTextDarkColor(R.color.white) //设置夜间默认文字颜色，不推荐设置
            .setBGColor(R.color.teal_700, R.color.color_blue_bg_normal) // 设置默认日间、夜间控件的背景色，不推荐设置
            .setBorderColor(R.color.purple_200, R.color.purple_200) // 设置默认日间、夜间控件的背景色，不推荐设置
            .setBorderWidth(3f) // 设置默认日间、夜间控件的背景色，不推荐设置
            .setIsRadiusAdjustBounds(true) // 设置默认日间、夜间控件的背景色，不推荐设置
            .setRadius(40f) // 设置控件背景的默认半径，不推荐设置
            .setRadiusBottomLeft(20f) // 设置控件背景的左下角默认半径，不推荐设置
            .setRadiusBottomRight(30f)*/ // 设置控件背景的右下角默认半径，不推荐设置
            .setRippleColor(R.color.color_ripple_light, R.color.color_ripple_dark) // 设置点击控件时的日间、夜间默认水波纹颜色，推荐设置
            .setRippleEnable(true) // 是否默认开启点击时的水波纹效果（只有设置点击监听事件的控件才有水波纹效果），推荐设置
            .build()
        AutoThemeManager.setDefaultParams(defaultParams)
    }
}
```
#### 3.在 xml布局文件中使用主题控件
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <com.codercool.autothemeview.view.ThemeTextView
        android:id="@+id/tv_go_theme_page"
        android:layout_width="200dp"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:paddingVertical="15dp"
        android:text="进入主题页面"
        android:textColor="@color/black"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:theme_backgroundColor="@color/color_gray_9"
        app:theme_backgroundDarkColor="@color/color_text_gray"
        app:theme_isRadiusAdjustBounds="true"
        app:theme_textDarkColor="@color/white" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

# TODO
ThemeImageView增加对圆角图片的支持
# 感谢
[QMUI_Android](https://github.com/Tencent/QMUI_Android)
