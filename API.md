- 登录信息
  - [x] 获取 GET /api/user/``user-id``/login-data
- 社团
  - [x] 获取所有社团名字图片描述 GET /api/club
  - [x] 获取某个社团的信息 GET /api/club/``club-id``/info
  - [x] 申请社团 POST /api/club
  - [x] 修改社团信息 PUT /api/club/``club-id``/info
  - [x] 获取创建社团的申请列表 GET /api/club/apply
  - [x] ~~同意某个社团的申请 PUT /api/club/apply/``club-id ``~~
  - [x] 获取我参加的社团 GET /api/user/joined-club
  - [x] 获取我创建的社团 GET /api/user/created-club
  - [x] 获取某个用户参加的社团 GET /api/user/``user-id``/joined-club
  - [x] 获取某个用户创建的社团 GET /api/user/``user-id``/created-club
- 消息
  - [x] 发布消息 POST /api/message
  - [x] 修改消息 PUT /api/message/``message-id``
  - [x] 获取某个消息详情 GET /api/message/``message-id``
  - [x] 获取用户的消息 GET /api/user/messages
  - [x] 获取我发布的消息 GET /api/user/my-messages
  - [x] 删除某个消息 DELETE /api/message/``message-id``
  - [x] 获取某个社团的所有消息 GET /api/club/``club-id``/message
  - [x] 已读某个消息 PUT /api/message/``message-id``/read
- 社团成员
  - [x] 申请加入某个社团 POST /api/club/``club-id``/apply
  - [x] 获取申请加入的成员 GET /api/club/``club-id``/apply
  - [x] 获取某个社团某个成员的加入信息 GET /api/club/``club-id``/user/``user-id``/info
  - [x] 修改某个社团某个成员的加入信息 PUT /api/club/``club-id``/user/``user-id``/info
  - [x] 同意某个成员的申请即为更改某个成员的角色
  - [x] 更改某人的角色 PUT /api/club/``club-id``/user/``user-id``/role
  - [x] 获取某个社团的所有成员id GET /api/club/``club-id``/member
  - [x] 踢出某个成员 DELETE /api/club/``club-id``/user/``user-id``
- 用户
  - [x] 获取用户信息 GET /api/user/``user-id``/info
  - [x] 更改用户信息 PUT /api/user/``user-id``/info
- 登录注册
  - [x] 登录 POST /api/login
  - [x] 注册 POST /api/register
- 图片
  - [x] 上传图片 POST /api/image
  - [x] 获取图片 GET /api/image/``picture-id``

```
club的progress
0表示保存草稿
1表示提交草稿，等待社联审核
2表示社联审核通过
3表示信息完善
```

```
club_member的role
0表示社长
1表示管理员
2表示普通成员
3表示未审核的成员
```

