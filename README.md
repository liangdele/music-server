<h1 align="center">music-website</h1>

<br/>

## 项目说明

本音乐网站的开发主要利用 VUE 框架开发前台和后台，后端接口用 Spring Boot + MyBatis 来实现，数据库使用的是 MySQL。

<br/>

## 项目截图

> 前台截图预览

![](https://tva1.sinaimg.cn/large/007S8ZIlly1geec0a2vd9j31c00u0n4z.jpg)<br/>

![](https://tva1.sinaimg.cn/large/007S8ZIlly1geec0qtdxrj31c00u07wj.jpg)<br/>

![](https://tva1.sinaimg.cn/large/007S8ZIlly1geec19x0e6j31c00u0npe.jpg)<br/>

![](https://tva1.sinaimg.cn/large/007S8ZIlly1geec1nmbt4j31c00u0hcf.jpg)<br/>

![](https://tva1.sinaimg.cn/large/007S8ZIlly1geec1yc0gkj31c00u0kjm.jpg)<br/>

![](https://tva1.sinaimg.cn/large/007S8ZIlly1geec29vvdtj31c00u0nok.jpg)<br/>

![](https://tva1.sinaimg.cn/large/007S8ZIlly1geec2ixqk1j31c00u0qf8.jpg)<br/>

![](https://tva1.sinaimg.cn/large/007S8ZIlly1geec31i06gj31c00u0wtw.jpg)<br/>

![](https://tva1.sinaimg.cn/large/007S8ZIlly1geec3ozxt9j31c00u0qbv.jpg)<br/>

![](https://tva1.sinaimg.cn/large/007S8ZIlly1geec41r7onj31c00u047y.jpg)<br/>

> 后台截图预览

![](https://tva1.sinaimg.cn/large/006tNbRwly1g9hhhu4n7tj31c00u04qq.jpg)<br/>

![](https://tva1.sinaimg.cn/large/007S8ZIlly1ghrnrvgflvj31c00u0jzh.jpg)<br/>

![](https://tva1.sinaimg.cn/large/007S8ZIlly1ghrns4ycpkj31c00u0qjl.jpg)<br/>

![](https://tva1.sinaimg.cn/large/007S8ZIlly1ghrnsfearcj31c00u0axt.jpg)<br/>

![](https://tva1.sinaimg.cn/large/007S8ZIlly1ghrnsq6s3sj31c00u0tmu.jpg)<br/>

![](https://tva1.sinaimg.cn/large/007S8ZIlly1ghrnszq403j31c00u07nq.jpg)<br/>

## 功能

- 音乐播放
- 用户登录注册
- 用户信息编辑、头像修改
- 歌曲、歌单搜索
- 歌单打分
- 歌单、歌曲评论
- 歌单列表、歌手列表分页显示
- 歌词同步显示
- 音乐收藏、下载、拖动控制、音量控制
- 后台对用户、歌曲、歌手、歌单信息的管理

<br/>

## 技术栈

#### 后端

SpringBoot + MyBatis

#### 前端

Vue + Vue-Router + Vuex + Axios +  ElementUI

<br/>

## 安装

#### 1、下载项目到本地

```
git clone https://gitee.com/ygh996/Music_recommend.git
```

#### 2、下载数据库中记录的资源（项目已经下好了不用下了）

去【链接: https://pan.baidu.com/s/1Qv0ohAIPeTthPK_CDwpfWg 提取码: gwa4 】下载网站依赖的歌曲及图片，将 data 夹里的文件直接放到 music-server 文件夹下。

<img src="https://tva1.sinaimg.cn/large/007S8ZIlly1gekwp2wqxuj311v0u0du2.jpg" height="200px"/>

#### 3、修改配置文件
1）创建数据库
将 `music-website/music-server/sql` 文件夹中的 tp_music.sql 文件导入数据库。

2）修改后端配置文件
去 `music-website/music-server/src/main/resources` 这个目录下的文件里修改自己的 spring.datasource.username 和 spring.datasource.password；
修改下图圈出来的的文件中 MyPicConfig 类下的 addResourceLocations方法中的路径，否则资源加载不了。（Mac 和 win 下路径有些差异，我的是 Mac 上的路径，win 上需要在 file: 后标明是哪个盘，例如：`file:C:\\user\\XXX\\`）

![](https://tva1.sinaimg.cn/large/00831rSTly1gd38cq6yhrj31zk0juk02.jpg)

#### 4、启动项目

music-server 是本项目的后端，用于监听前端发来的请求，提供响应。music-client 和 music-manage 都是本项目的前端部分，前者是前台，后者是后台。运行时后端必须启动，两个前端项目可以都启动，也可以只启动其中一个，他们是独立的。

- **启动后端**：进入 music-server 文件夹，运行下面命令启动服务器

```js
直接点击三角形run 或者在springboot 启动类 点击application启动

// 方法一
./mvnw spring-boot:run

// 方法二
mvn spring-boot:run // 前提装了 maven
```

- **启动前台**：进入 music-client 文件夹，运行下面命令启动前台项目

```js
npm install // 安装依赖

npm run dev // 启动前台项目
```

- **启动后台**：进入 music-manage 文件夹，运行下面命令启动后台管理项目

```js
npm install // 安装依赖

npm run dev // 启动后台管理项目
```

<br/>

