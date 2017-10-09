/**
 * Created by mfhj-dz-001-424 on 17/2/15.
 */
/**
 * Created by mfhj-dz-001-424 on 17/1/18.
 */
var uppart = new Vue({
    el: '#publishInfo',
    data: {
        useriD:0,  //用户的iD
        nickName:"小神经很ok", //用户的昵称
        userPhotoUrl:"", //这个应该有个默认的地址
        totalDays:30, //共计30天
        title:"给点爱，一起温暖世界", //Tweet的标题
        progressBar:0, //进度条
        endDate:"2017-10-10", //结束日期
        targetMoney:100000, //目标金额
        realMoney:1314,  //已抽到钱数
        helpTimes:100, //捐助次数
        tweet:"各位好心热心人士你们好！泣血跪求各位能施援手，共救我哥哥的命。我20岁叫赵艳芳。我哥叫赵家胜。我是她的妹妹。他今年22岁。于2017年1月8日上午发生车祸，他为了闪避车导致的。现在伤的非常严重。 身上多处骨折，肋骨也断了，脑部受到严重的脑震荡，有出血，水肿的现象，现在没有什么意识。肺部也受到很大的创伤，现在感染的很厉害，只能靠吸氧机呼吸。我家是单亲家庭。", //项目详情
        imgOneUrl:'http://localhost:8080/glove/resources/image/1.jpg', //照片的地址
        imgTwoUrl:"http://localhost:8080/glove/resources/image/2.jpg",
        imgThreeUrl:"http://localhost:8080/glove/resources/image/25.jpg",
        imgFourUrl:"http://localhost:8080/glove/resources/image/4.jpg",
        imgFiveUrl:"http://localhost:8080/glove/resources/image/5.jpg",
        imgSixUrl:"http://localhost:8080/glove/resources/image/6.jpeg",
        imgSevenUrl:"",
        imgEightUrl:"",
        authority:"互助管家", //认证机构
        monitoringGroup:"互助管家社会监督处",
        concreteStudent:"北京大学艾海涛", //具体负责人
        receiveMoneyMan:"王二五的爹" ,     //收款人
        needHelpMan:"王二五",     //受助人
        identityProve:2,  //身份证明已提交
        hospitalProve:2, //医院证明已提交
        villageProve:2,  //居委会证明已提交
        otherProve:2,  //其它证明已提交
        affirmPeopleCount:80, //证实人数
        defaultAffirmUrl:"", //当没有人证实时，就显示这个默认的图片
        affirmImgUrls:['http://localhost:8080/glove/resources/image/1.jpg','http://localhost:8080/glove/resources/image/5.jpg','http://localhost:8080/glove/resources/image/25.jpg','http://localhost:8080/glove/resources/image/4.jpg']

    },

    methods: {
        confirmIt: function (event) { //仅仅是设置弹出框出现，和设置confirm标志
            //console.log(event.target.getAttribute("data-reciver"))
            let modalReportDiv=document.getElementById ("modalReport");
            modalReportDiv.style.display="block";
            //这里设置modalReport的举报标志"report" ,证实标志就是confirm
            let  applyDiv = document.getElementById ("applyReport");
            // let  dataRCTag = applyDiv.getAttribute ("data-RCTag");
            applyDiv.setAttribute("data-RCTag","1"); //1代表证实，2代表举报

        },
        handleClick: function() {
        },
    },
    mounted: function () {
        this.$http.get('http://localhost:8080/glove/timelinetweet/info/1').then(response => {
            // console.log(response.body);
            this.bodyMsg=response.body;
            this.$nextTick(function() {
                //   console.log(this.bodyMsg);
            });
        }, err => {
            console.log(err);
        });
    },
})



