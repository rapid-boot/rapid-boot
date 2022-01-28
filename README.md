# rapid-boot

## quick start

首先创建一张 MySQL 表：

```sql

--
-- 表的结构 `t_login_app_history`
--

CREATE TABLE `t_login_app_history` (
  `id` bigint(20) NOT NULL,
  `loginId` bigint(20) NOT NULL COMMENT '登录ID',
  `acction` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '登录账号',
  `role` int(2) DEFAULT NULL COMMENT '角色 1-客户 2-车辆',
  `loginIp` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '登录IP',
  `loginTime` datetime NOT NULL COMMENT '登录时间',
  `loginMethod` int(4) NOT NULL COMMENT '登录方式 1-自动登录 2-账号密码登录',
  `loginType` int(4) NOT NULL COMMENT '登录介质 1-android 2-IOS 3-PC 4-WAP',
  `token` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '登录token',
  `uuid` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '登录介质的UUID',
  `appVersion` varchar(24) COLLATE utf8_bin DEFAULT NULL COMMENT '当前软件版本',
  `vendor` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '手机设备厂家',
  `model` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '手机型号',
  `sysVersion` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '手机系统版本号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='APP登录信息记录表' ROW_FORMAT=DYNAMIC;

--
-- 转存表中的数据 `t_login_app_history`
--

INSERT INTO `t_login_app_history` (`id`, `loginId`, `acction`, `role`, `loginIp`, `loginTime`, `loginMethod`, `loginType`, `token`, `uuid`, `appVersion`, `vendor`, `model`, `sysVersion`) VALUES
(1, 13, '17324801005', 2, '172.16.100.5', '2020-05-27 15:54:28', 2, 1, '6565663065663236353539396430323935646262373465303931346465306630', '867960034452828,867960034501590', 'Android-1.0.3', 'HUAWEI', 'VTR-AL00', '9'),
(2, 13, '17324801005', 2, '172.16.100.5', '2020-05-27 15:54:46', 2, 1, '6531343862623736633964616166386533356563353165306137356439363863', '867960034452828,867960034501590', 'Android-1.0.3', 'HUAWEI', 'VTR-AL00', '9'),
(3, 39, '15839503001', 1, '172.16.100.5', '2020-05-29 11:11:36', 2, 1, '3635313664313238366234313930396635383066336534626666396266343161', '867960034452828,867960034501590', 'Android-1.0.6', 'HUAWEI', 'VTR-AL00', '9'),
(4, 19, '15839502002', 1, '172.16.100.5', '2020-05-29 16:51:24', 2, 1, '3738306133343333616663663464393262656331303862366163303536363361', '867960034452828,867960034501590', 'Android-1.0.6', 'HUAWEI', 'VTR-AL00', '9'),
(5, 19, '15839502002', 1, '172.16.100.5', '2020-05-29 16:52:22', 2, 1, '3631386337313739613262616436393031353365653231323535336562346332', '867960034452828,867960034501590', 'Android-1.0.6', 'HUAWEI', 'VTR-AL00', '9'),
(6, 19, '15839502002', 1, '172.16.100.5', '2020-06-01 10:47:49', 2, 1, '3565666436353463613136363362313830356636306337613163633333356338', '867960034452828,867960034501590', 'Android-1.0.6', 'HUAWEI', 'VTR-AL00', '9'),
(7, 39, '15839503001', 1, '172.16.100.5', '2020-06-01 11:10:09', 2, 1, '6433376465356335653638353132663137663134373631346364663365396533', '867960034452828,867960034501590', 'Android-1.0.6', 'HUAWEI', 'VTR-AL00', '9');


--
-- 表的索引 `t_login_app_history`
--
ALTER TABLE `t_login_app_history`
    ADD PRIMARY KEY (`id`) USING BTREE;

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `t_login_app_history`
--
ALTER TABLE `t_login_app_history`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=804;
COMMIT;


```

然后配置数据库连接：

1. rapid-generator 模块：com.rapidboot.generator.dao.DbAccess#getConnection
2. demo 模块：rapid-springboot-demo/src/main/resources/application.yml

运行 rapid-generator，生成代码。再运行 demo 模块，api 启动。

测试：<http://127.0.0.1:8080/api/loginAppHistory/list>
