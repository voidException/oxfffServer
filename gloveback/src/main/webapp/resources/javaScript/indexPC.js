/**
 * Created by aihaitao on 27/3/2017.
 */
/**
 * Created by mfhj-dz-001-424 on 17/1/18.
 */



var mainBody = new Vue({
    el: '#mainBody',
    data:{
        "productInfos":
         [
            {
                "productid": 6,
                "productuuid": "6_skjsdjkkdssdlfksd",
                "nickname": "云南特产",
                "userid": 106,
                "useruuid": "106_r324324",
                "photourl": "http://onejf30n8.bkt.clouddn.com/userPhoto5.jpeg",
                "wechat": "beibeixfkl",
                "phone": "15652649121",
                "checkprogress": "审核通过",
                "checkeruuid": "101_r324324",
                "checkdate": 1488771448000,
                "targetmoney": 5000,
                "handymoney": 4320,
                "backuptimes": 250,
                "reporttimes": 0,
                "producttype": "农产品",
                "starttime": 1488777676000,
                "endtime": 1494048076000,
                "chixutime": 60,
                "endtag": 1,
                "trafficmoney": "全国包邮（新疆，西藏不发货；海南、内蒙、东北三省补差5元）",
                "trafficdate": "48H内发货",
                "paysampleone": 5,
                "sampleonedsp": "新疆，西藏不发货；海南、内蒙、东北三省补差5元",
                "paysampletwo": 35,
                "sampletwodsp": "【惠5元】冷鲜爽口 陕西冰糖红富士 总箱重10斤 规格75-80mm",
                "paysamplethree": 39,
                "samplethreedsp": "【39元】冷鲜爽口 陕西冰糖红富士 总箱重10斤 规格75-80mm",
                "paysamplefour": 45,
                "samplefourdsp": "45元】冷鲜爽口 陕西冰糖红富士 总箱重10斤 规格85mm左右",
                "paysamplefive": 60,
                "samplefivedsp": "60元】冷鲜爽口 陕西冰糖红富士 总箱重20斤 规格85mm左右",
                "aftermarket": "电话联系15652649121",
                "maintitleone": "【食责险保障】陕西苹果，10斤感恩35元",
                "vicetitleone": "陕西关中素有“八百里秦川”的美誉，其渭河北部的旱原被联合国评为“最适合苹果生长的地区”\n这里海拔高、昼夜温差大、光照时间长，在这样的环境下长出的苹果，果实端庄、色泽鲜艳、口感清脆。2016年陕西苹果种植面积达到1100万亩，产量将超1100万吨，占全国产量的1/4,世界的1/7，可以说世界上每7个苹果就有一个出自陕西。",
                "imgone": "http://onejf30n8.bkt.clouddn.com/353f6e49-ffdc-4d00-9ad8-ca343f6154c72.png",
                "maintitletwo": "我们的成就",
                "vicetitletwo": "进行的“爱心助农”义购得到当地镇政府认可与特别支持;",
                "imgtwo": "http://onejf30n8.bkt.clouddn.com/353f6e49-ffdc-4d00-9ad8-ca343f6154c73.jpg",
                "maintitlethree": "丰收的喜悦难言忧愁",
                "vicetitlethree": "一大清早起来，带领小团队进入农户家中的果库安排分拣装箱工作...现场忙的不亦乐乎!果农脸上放下久凝的忧愁；",
                "imgthree": "http://onejf30n8.bkt.clouddn.com/353f6e49-ffdc-4d00-9ad8-ca343f6154c74.jpg",
                "maintitlefour": "案例一",
                "vicetitlefour": "果       农：李小娟\n种植品种：高山红富士\n产       量：18000KG左右\n销售情况：未销售，处于滞销状态",
                "imgfour": "http://onejf30n8.bkt.clouddn.com/353f6e49-ffdc-4d00-9ad8-ca343f6154c76.png",
                "maintitlefive": "富士苹果",
                "vicetitlefive": "这就是传说中的高海拔、大温差造就的冰糖心红富士苹果；",
                "imgfive": "http://onejf30n8.bkt.clouddn.com/353f6e49-ffdc-4d00-9ad8-ca343f6154c77.jpg",
                "maintitlesix": "我们的承诺",
                "vicetitlesix": "我们承诺给到客户的产品不打蜡，不打药，新鲜脆甜，采摘鲜发；",
                "imgsix": "http://onejf30n8.bkt.clouddn.com/353f6e49-ffdc-4d00-9ad8-ca343f6154c78.jpg",
                "maintitleseven": "—— 返乡大学生助农",
                "vicetitleseven": "陕西在行动，感谢有你",
                "imgseven": "http://onejf30n8.bkt.clouddn.com/353f6e49-ffdc-4d00-9ad8-ca343f6154c75.png",
                "maintitleeight": null,
                "vicetitleeight": null,
                "imgeight": null
            },
            {
                "productid": 5,
                "productuuid": "5_skdkjsnkdsffkdsfkn",
                "nickname": "东阿阿胶",
                "userid": 105,
                "useruuid": "105_r324324",
                "photourl": "http://onejf30n8.bkt.clouddn.com/userPhoto4.jpeg",
                "wechat": "wxid_kyk2vie2yevc22",
                "phone": "15652649121",
                "checkprogress": "审核通过",
                "checkeruuid": "101_r324324",
                "checkdate": 1488685042000,
                "targetmoney": 10000,
                "handymoney": 7380,
                "backuptimes": 0,
                "reporttimes": 0,
                "producttype": "农产品",
                "starttime": 1488691270000,
                "endtime": 1493961670000,
                "chixutime": 60,
                "endtag": 1,
                "trafficmoney": "包邮",
                "trafficdate": "3天内",
                "paysampleone": 52,
                "sampleonedsp": "手工阿胶糕150g，二款口味可留言任选，（原味、枣杞混合味）默认发枣杞混合味",
                "paysampletwo": 85,
                "sampletwodsp": "回馈优惠价，买半斤送半斤！500克一盒，二款口味可留言任选（原味、枣杞混合）默认发枣杞混合味",
                "paysamplethree": 139,
                "samplethreedsp": "回馈优惠价，买一斤送一斤共2盒阿胶糕，二款口味可留言任选（原味、枣杞混合味)默认发枣杞混合味",
                "paysamplefour": 259,
                "samplefourdsp": "手工阿胶糕500g×2盒，二款口味可留言任选，（原味、枣杞混合味）默认发枣杞混合味",
                "paysamplefive": 378,
                "samplefivedsp": "手工阿胶糕500g×3盒，二款口味可留言任选，（原味、枣杞混合味）默认发枣杞混合味",
                "aftermarket": "如有问题可联系我：微信：13168525974  电话：13168525974",
                "maintitleone": "滋补礼品--美丽女人吃养颜『阿胶糕』",
                "vicetitleone": "女人如花，血为根本，补血养颜最佳食补莫过于手工阿胶糕。女人一生承载着太多身份，为人女、为人妻、为人母，在关爱亲人的同时更要好好爱护自己。",
                "imgone": "http://onejf30n8.bkt.clouddn.com/28c68329-1bb0-4183-88c7-38d2550faa071.jpg",
                "maintitletwo": "富含营养",
                "vicetitletwo": "富含多种营养",
                "imgtwo": "http://onejf30n8.bkt.clouddn.com/28c68329-1bb0-4183-88c7-38d2550faa072.jpg",
                "maintitlethree": "虽为食品",
                "vicetitlethree": "补品，看起来也很美哦",
                "imgthree": "http://onejf30n8.bkt.clouddn.com/28c68329-1bb0-4183-88c7-38d2550faa073.jpg",
                "maintitlefour": "由内而外",
                "vicetitlefour": "充满了美",
                "imgfour": "http://onejf30n8.bkt.clouddn.com/28c68329-1bb0-4183-88c7-38d2550faa074.jpg",
                "maintitlefive": "制作方法",
                "vicetitlefive": "传统方法，手工制作",
                "imgfive": "http://onejf30n8.bkt.clouddn.com/28c68329-1bb0-4183-88c7-38d2550faa075.jpg",
                "maintitlesix": "功效",
                "vicetitlesix": "补血，滋阴",
                "imgsix": "http://onejf30n8.bkt.clouddn.com/28c68329-1bb0-4183-88c7-38d2550faa076.jpg",
                "maintitleseven": "实物展示",
                "vicetitleseven": "一次让你看个够",
                "imgseven": "http://onejf30n8.bkt.clouddn.com/28c68329-1bb0-4183-88c7-38d2550faa077.jpg",
                "maintitleeight": null,
                "vicetitleeight": null,
                "imgeight": null
            },
            {
                "productid": 4,
                "productuuid": "4_98329498dsdfk",
                "nickname": "新妈妈",
                "userid": 104,
                "useruuid": "104_r324324",
                "photourl": "http://onejf30n8.bkt.clouddn.com/userPhoto3.jpeg",
                "wechat": "陌尚花开",
                "phone": "15652649121",
                "checkprogress": "审核通过",
                "checkeruuid": "101_r324324",
                "checkdate": 1488598568000,
                "targetmoney": 6000,
                "handymoney": 3690,
                "backuptimes": 50,
                "reporttimes": 0,
                "producttype": "农产品",
                "starttime": 1488604863000,
                "endtime": 1493875257000,
                "chixutime": 60,
                "endtag": 1,
                "trafficmoney": "包邮",
                "trafficdate": "48小时内",
                "paysampleone": 45,
                "sampleonedsp": "支持45 元 回报喀什特大无花果500g 申通航空包邮",
                "paysampletwo": 74,
                "sampletwodsp": "原价78 现活动优惠9.5折 74元 回报喀什新疆风味特大无花果干2斤 航空包邮",
                "paysamplethree": 76,
                "samplethreedsp": "支持76元 回报喀什新疆风味 小无花果干2斤 航空包邮",
                "paysamplefour": 98,
                "samplefourdsp": "支持98元 回报喀什特大无花果1斤 小无花果1斤 和田大枣1斤",
                "paysamplefive": 156,
                "samplefivedsp": "支持156 回报喀什特大无花果2斤 小无花果2斤 申通航空包邮",
                "aftermarket": "联系微信：18154917812（喀什特大无花果干）\n联系电话：18154917812（喀什特大无花果干）",
                "maintitleone": "喀什特大无花果-源自天然，品味健康！",
                "vicetitleone": "我叫果果，来自新疆    幸福人生，从美味开始。\n我的家在新疆喀什，一个古老而又贫穷的城市，\n先给大家介绍介绍我的家乡。",
                "imgone": "http://onejf30n8.bkt.clouddn.com/16acb151-788d-4966-8991-17c76e4d117b1.jpg",
                "maintitletwo": "喀什简介",
                "vicetitletwo": "喀什是一个多名族聚集的地区，许多古老民族曾在这里繁衍生息，发展经济文化。在漫长的生辉进程中，个名族相互协作，互相影响。逐渐完成了现代民族发展的进程。",
                "imgtwo": "http://onejf30n8.bkt.clouddn.com/16acb151-788d-4966-8991-17c76e4d117b2.jpg",
                "maintitlethree": "在给大家介绍介绍我们家乡的特产—特大无花果干",
                "vicetitlethree": "无花果：喀什盛产五花挂，他可与哈密瓜，吐鲁番葡萄，库尔勒香梨齐名。无花果果形扁圆。果肉细软果味甘甜，营养丰富并有要用价值",
                "imgthree": "http://onejf30n8.bkt.clouddn.com/16acb151-788d-4966-8991-17c76e4d117b3.jpg",
                "maintitlefour": "香甜可口，馋人吗",
                "vicetitlefour": "无花果可食率高，鲜果可食用部分达97%，干果和蜜饯类达100%，且含酸量低，无硬大的种子，因此尤适老人和儿童食用",
                "imgfour": "http://onejf30n8.bkt.clouddn.com/16acb151-788d-4966-8991-17c76e4d117b4.jpg",
                "maintitlefive": "异域风味",
                "vicetitlefive": "无花果含有苹果酸、柠檬酸、脂肪酶、蛋白酶、水解酶等，能帮助人体对食物的消化，促进食欲，又因其含有多种脂类，故具有润肠通便的效果",
                "imgfive": "http://onejf30n8.bkt.clouddn.com/16acb151-788d-4966-8991-17c76e4d117b5.jpg",
                "maintitlesix": "甘甜芳香，有益健康",
                "vicetitlesix": "无花果所含的脂肪酶、水解酶等有降低血脂和分解血脂的功能，可减少脂肪在血管内的沉积，进而起到降血压、预防冠心病的作用。",
                "imgsix": "http://onejf30n8.bkt.clouddn.com/16acb151-788d-4966-8991-17c76e4d117b6.jpg",
                "maintitleseven": "更多功效",
                "vicetitleseven": "无花果有祛痰理气，润肺止咳，解读润肠功效。",
                "imgseven": "http://onejf30n8.bkt.clouddn.com/16acb151-788d-4966-8991-17c76e4d117b7.jpg",
                "maintitleeight": "我们都是来自美丽的喀什",
                "vicetitleeight": "我想将我家乡的特产分享给大家，非常感谢给爱网这个平台。通过这个平台我能像大家展示自己的家乡。",
                "imgeight": "http://onejf30n8.bkt.clouddn.com/16acb151-788d-4966-8991-17c76e4d117b8.jpg"
            },
            {
                "productid": 3,
                "productuuid": "3_sdkuuaiuacnvc",
                "nickname": "领路人",
                "userid": 103,
                "useruuid": "103_r324324",
                "photourl": "http://onejf30n8.bkt.clouddn.com/userPhoto4.jpeg",
                "wechat": "aiwenqiang0814",
                "phone": "15652649121",
                "checkprogress": "审核通过",
                "checkeruuid": "101_r324324",
                "checkdate": 1488512161000,
                "targetmoney": 5000,
                "handymoney": 1020,
                "backuptimes": 201,
                "reporttimes": 0,
                "producttype": "农产品",
                "starttime": 1488518457000,
                "endtime": 1493788857000,
                "chixutime": 60,
                "endtag": 1,
                "trafficmoney": "包邮（海南、云南、西藏、新疆、内蒙除外",
                "trafficdate": "每3天发一次货",
                "paysampleone": 50,
                "sampleonedsp": "获得大荔卤肉1斤包邮，点击左下角五角星添加关注，随时查询快递动态",
                "paysampletwo": 55,
                "sampletwodsp": "获得五香猪蹄一只，一只大概500—600g，真空包邮。点击左下角五角星添加关注，随时查询快递动态",
                "paysamplethree": 68,
                "samplethreedsp": "获得500g【陕西五香腊牛肉】真空包装包邮（偏远地方不包邮），点击左下角五角星随时关注物流.",
                "paysamplefour": 125,
                "samplefourdsp": "获得900g【陕西五香腊牛肉】真空包装包邮（偏远地方不包邮），点击左下角五角星随时关注物流.",
                "paysamplefive": 160,
                "samplefivedsp": "特价】获得1斤陕西腊牛肉、1斤大荔卤肉、1支五香猪蹄，真空包邮，点击左下角五角星添加关注。",
                "aftermarket": "收到快递后，请打开包装箱，发现有问题的话，请在24小时内及时联系我，我将为您尽快处理。我的手机号是：18700370715，微信号是：779124220.",
                "maintitleone": "陕西五香腊牛肉、大荔特色卤肉、五香猪蹄",
                "vicetitleone": "大家好！非常荣幸能够在轻松筹平台与诸位相识，我是一位来自陕西的朋友。想必来陕西旅游的朋友都不会忘记这里险峻的华山与沧桑的历史古城，当然还有我们陕西当地的地道小吃美食。今天，我将为大家推荐一款极具西北风味的特色美食——陕西五香腊牛肉。\n        陕西五香腊牛肉是陕西的特产，是西北人民最喜欢的风味食品。五香腊牛肉属于清真食品，风味独特，别具一格。腊牛肉营养丰富，肉色红润，肉质酥松，不腥不膳，味美爽口，色香味俱全。",
                "imgone": "http://onejf30n8.bkt.clouddn.com/7f6343ea-83a5-4a27-9cb2-be2e5c91d67e1.jpg",
                "maintitletwo": "干爽结实，绵软耐品，咸度适中，腊香浓郁，鲜美味厚，脍炙人口",
                "vicetitletwo": "陕西五香腊牛肉的选材十分讲究，一般选用陕西当地的秦川牛。秦川牛是中国著名的大型役肉兼用品种牛，原产陕西省渭河流域的关中平原地区。秦川牛易于育肥，肉质细致，瘦肉率高，大理石纹明显，用来加工腊牛肉再合适不过了",
                "imgtwo": "http://onejf30n8.bkt.clouddn.com/7f6343ea-83a5-4a27-9cb2-be2e5c91d67e2.png",
                "maintitlethree": "陕西五香腊牛肉的制作工艺",
                "vicetitlethree": "经过捋顺、清打、去除掉边角料，只留下精选肉。经过食盐和井水腌制一个星期，每天起码要两个小时要搅一次肉，本地叫做“捣缸”，实际上就是在里面搅动肉，让肉上下翻动.",
                "imgthree": "http://onejf30n8.bkt.clouddn.com/7f6343ea-83a5-4a27-9cb2-be2e5c91d67e3.jpg",
                "maintitlefour": "耐心等待。。。",
                "vicetitlefour": "经过7天的腌制，当牛肉鲜红的颜色退去后，再放入老汤中炖煮。老汤是牛骨头熬的清汤，炖肉时还要依次加入大料、花椒、香砂、茴香、梁姜、白芷、桂皮、草果、盐以及红曲米等等独家配料。这些配料使用的多少都要经过严格的计算称量。经过12个小时文火慢炖之后，炖熟的腊牛肉色泽红润、又酥又烂，香气四溢、入口即化",
                "imgfour": "http://onejf30n8.bkt.clouddn.com/7f6343ea-83a5-4a27-9cb2-be2e5c91d67e4.jpg",
                "maintitlefive": "陕西五香腊牛肉的特点",
                "vicetitlefive": "1  陕西腊牛肉的制作过程，采用的是中国传统的配方佐料，不含任何添加剂，防腐剂；\n2  制作过程没有烟熏，油炸等，完全采用蒸煮的方式——安全、健康；\n3  陕西腊牛肉保持肉类原有的香味，真空包装、食用方便",
                "imgfive": "http://onejf30n8.bkt.clouddn.com/7f6343ea-83a5-4a27-9cb2-be2e5c91d67e5.png",
                "maintitlesix": "陕西五香腊牛肉的功效",
                "vicetitlesix": "1. 牛肉富含蛋白质，氨基酸组成比猪肉更接近人体需要，能提高机体抗病能力，对生长发育及术后，病后调养的人在补充失血、修复组织等方面特别适宜;\n2. 牛肉有补中益气，滋养脾胃，强健筋骨，化痰息风，止渴止涎之功效，适宜于中气下隐、气短体虚、筋骨酸软、贫血久病及面黄目眩之人食用;\n3. 黄牛肉能安中益气、健脾养胃、强筋壮骨",
                "imgsix": "http://onejf30n8.bkt.clouddn.com/7f6343ea-83a5-4a27-9cb2-be2e5c91d67e6.jpg",
                "maintitleseven": "关于陕西五香腊牛肉的食用",
                "vicetitleseven": "打开真空袋切片、切块凉拌（西北吃法）",
                "imgseven": "http://onejf30n8.bkt.clouddn.com/7f6343ea-83a5-4a27-9cb2-be2e5c91d67e7.jpg",
                "maintitleeight": "野外食用",
                "vicetitleeight": "可作为野外充饥、补充能量的零食，打开真空袋即可食用；",
                "imgeight": "http://onejf30n8.bkt.clouddn.com/7f6343ea-83a5-4a27-9cb2-be2e5c91d67e8.jpg"
            },
            {
                "productid": 2,
                "productuuid": "2_sskdfnnsdsoddsfj",
                "nickname": "一介农夫",
                "userid": 102,
                "useruuid": "102_r324324",
                "photourl": "http://onejf30n8.bkt.clouddn.com/userPhoto2.jpeg",
                "wechat": "苦咖啡",
                "phone": "15652649121",
                "checkprogress": "审核通过",
                "checkeruuid": "101_r324324",
                "checkdate": 1488425756000,
                "targetmoney": 10000,
                "handymoney": 4110,
                "backuptimes": 50,
                "reporttimes": 0,
                "producttype": "农产品",
                "starttime": 1488432052000,
                "endtime": 1493702452000,
                "chixutime": 60,
                "endtag": 1,
                "trafficmoney": "包邮 偏远补运费差价",
                "trafficdate": "下单48小时内",
                "paysampleone": 8,
                "sampleonedsp": "（西藏，港澳台，海南，不发货。内蒙，新疆，吉林，青海，宁夏，甘肃）。需补运费差价补运费差价",
                "paysampletwo": 35,
                "sampletwodsp": "5斤装净３5CM左右 家庭自食 物美价廉长期食用请冰箱保鲜冷藏 温度不高地区通风阴凉处保存",
                "paysamplethree": 40,
                "samplethreedsp": "净重5斤装长度40ｃｍ 有粗度　家庭自用 长期食用请冰箱保鲜冷藏 温度不高地区通风阴凉处保存",
                "paysamplefour": 50,
                "samplefourdsp": "手工制作去皮山药干 500g 不含硫（色泽不会特别的白亮，不尝后悔了）适合长期保存",
                "paysamplefive": 76,
                "samplefivedsp": "净重8斤长度40ｃｍ左右粗度好家庭自用 朋友互赠长期食用请冰箱保鲜冷藏 温度不高地区阴凉处保存",
                "aftermarket": "13203936364  所有售后问题请平台留言或联系我们",
                "maintitleone": "国家地域性保护产品，温县垆土铁棍山药",
                "vicetitleone": "平台给无助的农民带来了希望，让我们这些因无滋补效果的假货次品而滞销的地道温县垆土地铁棍山药能通过平台走进大家的餐桌！我们是农户自产自销！（本期有手工山药干了，没有机械，没有添加，品质杠杠的）\n　　　温县垆土地铁棍山药，是国家原产地域性保护品种！我们是温县的农民， 世代种植山药。对山药的选地，种植方法，施肥管控，品种的选择，等  有多年的经验积累。在此保证回报给朋友的每一根都是地道优质的温县垆土地铁棍山药。好山药你吃了就知道！(请在收到宝贝后，去掉所有包装，通风阴凉处保存）",
                "imgone": "http://onejf30n8.bkt.clouddn.com/5dca1c07-8227-4e51-b437-86d921926a4e1.jpg",
                "maintitletwo": "山药的种植采挖",
                "vicetitletwo": "从翻地播种，手工除草，到深沟采挖........处处流淌着农民的汗水",
                "imgtwo": "http://onejf30n8.bkt.clouddn.com/5dca1c07-8227-4e51-b437-86d921926a4e2.jpg",
                "maintitlethree": "产品特征及滋补效果",
                "vicetitlethree": "温县垆土地山药特点突出表面红斑明显，粗细不均！因为垆土的土壤特别粘重， 各种元素丰富，是自古以来优秀的山药种植区!种出的铁棍山药甜，面，粉性十足!微量元素含量高，特别是DHEA（青春因子）的含量是其他地域的山药无法比拟的。山药属于药食同源山药被用作药物至今已有2000年以上的历史，在《神农本草经》中就有关于山药的记载，认为山药“补中，益气力，长肌肉”，就是说山药具有补虚、除寒热邪气、补中益气，生肌等功效。历代中医药著作均肯定了山药补脾养胃、生津益肺、补肾涩精的功效如《本草纲目》中就认为山药“益肾气，健脾胃”。“怀山药”也曾在巴拿马万国博览会上展出，遂蜚声中外，历年来向英、美等十多个国家和地区出口。温县垆土地铁棍山药独特的药食同补，常食之　对肾虚无力，肺虚咳嗽，气虚面黄，都有奇效。",
                "imgthree": "http://onejf30n8.bkt.clouddn.com/5dca1c07-8227-4e51-b437-86d921926a4e3.jpg",
                "maintitlefour": "保存及食用方法",
                "vicetitlefour": "收到货后需及时去掉所有包装，于阴凉通风处保存。 山药的多种食用方法中清蒸铁棍山药是最简单的吃法，原汁原味，制作过程中营养能被很好的保存下来，简单易做。将山药洗净，去皮，切断上蒸笼，水开后不到10分钟就可以闻到铁棍山药特有的清香！15分钟后，关火，出笼，不管是直接食用还是蘸上白糖，  甜，面，美啊！ 当然，熬甜粥，拔丝山药，炖排骨汤也都是可以的！我们的手工山药片也做好了！品质杠杠的！！！",
                "imgfour": "http://onejf30n8.bkt.clouddn.com/5dca1c07-8227-4e51-b437-86d921926a4e4.jpg",
                "maintitlefive": "实物展示",
                "vicetitlefive": "手工切片容易保存",
                "imgfive": "http://onejf30n8.bkt.clouddn.com/5dca1c07-8227-4e51-b437-86d921926a4e5.jpg",
                "maintitlesix": "还有证明哦",
                "vicetitlesix": "证明在手，值得信赖",
                "imgsix": "http://onejf30n8.bkt.clouddn.com/5dca1c07-8227-4e51-b437-86d921926a4e6.jpg",
                "maintitleseven": "好土地",
                "vicetitleseven": "肥沃的土地，长出上好的农产品",
                "imgseven": "http://onejf30n8.bkt.clouddn.com/5dca1c07-8227-4e51-b437-86d921926a4e7.jpg",
                "maintitleeight": null,
                "vicetitleeight": null,
                "imgeight": null
            },
            {
                "productid": 1,
                "productuuid": "1_98398dnjsfnkfkjsfn",
                "nickname": "沂蒙山人",
                "userid": 101,
                "useruuid": "101_r324324",
                "photourl": "http://onejf30n8.bkt.clouddn.com/userPhoto6.jpeg",
                "wechat": "putaozhuose",
                "phone": "15652649121",
                "checkprogress": "审核通过",
                "checkeruuid": "101_r324324",
                "checkdate": 1488339347000,
                "targetmoney": 15000,
                "handymoney": 6020,
                "backuptimes": 243,
                "reporttimes": 0,
                "producttype": "农产品",
                "starttime": 1488340420000,
                "endtime": 1493610820000,
                "chixutime": 60,
                "endtag": 1,
                "trafficmoney": "免运费",
                "trafficdate": "众筹结束，立马发货",
                "paysampleone": 42,
                "sampleonedsp": "限量优惠50份！回报5斤装65-80伦晚橙一箱",
                "paysampletwo": 50,
                "sampletwodsp": "回报5斤装春橙伦晚65-80果一箱",
                "paysamplethree": 80,
                "samplethreedsp": "回报净果9斤装65-80果径伦晚一箱",
                "paysamplefour": 100,
                "samplefourdsp": "回报净果9斤装75-90果径伦晚一箱",
                "paysamplefive": 110,
                "samplefivedsp": "回报净果9斤装85-100果径伦晚一箱",
                "aftermarket": "我们承诺橙子现摘现发，坏果包赔、少称包赔，如收到有坏果请在12小时内拍照联系售后处理",
                "maintitleone": "橙中皇后",
                "vicetitleone": "本期我们发起众筹的品类是秭归脐橙众多品类中只有春天才能吃到的甜橙堪称口感上佳的橙中皇后－－春橙伦晚！\n      伦晚脐橙属晚熟脐橙品种，翌年3月中下旬成熟。果实近圆球形，平均单果重200克以上;果皮浅橙红色皮硬光滑，较易剥皮;肉质致密脆嫩，汁多化渣，可溶性固形物含量12.5%以上，果果汁率45.5%，可食率74.1%.",
                "imgone": "http://onejf30n8.bkt.clouddn.com/2c02eb99-dc14-4ab8-8bad-27474e2bf4791.jpg",
                "maintitletwo": null,
                "vicetitletwo": "它，皮薄脐小，色泽诱人，拿起轻嗅，还略带一丝橙花的清新香味。它，有个动人的名字：伦晚脐橙",
                "imgtwo": "http://onejf30n8.bkt.clouddn.com/2c02eb99-dc14-4ab8-8bad-27474e2bf4792.jpg",
                "maintitlethree": null,
                "vicetitlethree": "【花果同枝——伦晚】唯一一款花果同枝的橙子，口感细腻，9分甜1分酸，好吃到爆，非常好吃的橙子，没有之一",
                "imgthree": "http://onejf30n8.bkt.clouddn.com/2c02eb99-dc14-4ab8-8bad-27474e2bf4793.jpg",
                "maintitlefour": null,
                "vicetitlefour": "诗意伦晚，一份有情怀的甜橙!",
                "imgfour": "http://onejf30n8.bkt.clouddn.com/2c02eb99-dc14-4ab8-8bad-27474e2bf4794.jpg",
                "maintitlefive": null,
                "vicetitlefive": "花果同枝，既开花又挂果",
                "imgfive": "http://onejf30n8.bkt.clouddn.com/2c02eb99-dc14-4ab8-8bad-27474e2bf4795.png",
                "maintitlesix": null,
                "vicetitlesix": "爽甜可口",
                "imgsix": "http://onejf30n8.bkt.clouddn.com/2c02eb99-dc14-4ab8-8bad-27474e2bf4796.png",
                "maintitleseven": null,
                "vicetitleseven": "伦晚虽好、采食不易！",
                "imgseven": "http://onejf30n8.bkt.clouddn.com/2c02eb99-dc14-4ab8-8bad-27474e2bf4797.jpg",
                "maintitleeight": null,
                "vicetitleeight": "好橙不怕晚！",
                "imgeight": "http://onejf30n8.bkt.clouddn.com/2c02eb99-dc14-4ab8-8bad-27474e2bf4798.jpg"
            },
         ],
        loginTag:false ,
        initPage:0,
    },
    methods: {
        nextPage: function (event) {
            this.initPage+=6;
            if (this.initPage>6){ //因为数据只有两页、
                this.initPage-=6;
                return
            }
            let param={
                "page":this.initPage,
                "pageSize":5,
            };
            let options={'Content-Type':'application/json'};
            this.$http.post('http://www.geilove.org/glove/productInfo/listProduct',param,options).then(response => {
                console.log(response.body);
                this.productInfos=response.body.productInfos;

                this.$nextTick(function() {
                    //   console.log(this.bodyMsg);
                });
            }, err => {
                console.log(err);
            });

        },
        backPage: function (event) {
            this.initPage-=6;
            if (this.initPage<0){ //因为数据只有两页,<0的时候是-6
                this.initPage+=6;
                return
            }
            let param={
                "page":this.initPage,
                "pageSize":5,
            };
            let options={'Content-Type':'application/json'};
            this.$http.post('http://wwww.geilove.org/productInfo/listProduct',param,options).then(response => {
                console.log(response.body);
                this.productInfos=response.body.productInfos;

                this.$nextTick(function() {
                    //   console.log(this.bodyMsg);
                });
            }, err => {
                console.log(err);
            });

        },
        handleClick2: function() {
            this.updateORprogress=false;
            this.updateORprogress2=true;
            var div=document.getElementById("test");
            console.log(div.getAttribute("type")); //huifu
            div.setAttribute("type","update");
            console.log(div.getAttribute("type")); //update
        },
    },
    mounted: function () {

        let param={
            "page":this.initPage,
            "pageSize":5,
        };
        let options={'Content-Type':'application/json'};
        this.$http.post('http://www.geilove.org/productInfo/listProduct',param,options).then(response => {
             console.log(response.body);
            this.productInfos=response.body.productInfos;

            this.$nextTick(function() {
                //   console.log(this.bodyMsg);
            });
        }, err => {
            console.log(err);
        });

       // 检车是否登录
        if (window.localStorage.getItem("usernickname")!==null){
            this.loginTag=true;
        }
        setTimeout(()=>{
            window.localStorage.setItem("usernickname",null);
            this.loginTag=false;
        },30*60*1000)

    },

})



