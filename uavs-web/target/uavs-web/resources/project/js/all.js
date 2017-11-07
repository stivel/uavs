$(function(){
    if($('header').next('.index').length>0){
        $('header').addClass('index');
    }
    $(document).scroll(function () {
        var nHeight = $(window).height();
        var nScrollTop = $(document).scrollTop();
        if(nScrollTop>nHeight&&$('header').hasClass('index')){
            $('header').addClass('fixed');
        }else{
            $('header').removeClass('fixed');
        }
    });

    //趋势图
    $(function () {
        var aDiv = $('.trends-dot>div:gt(0)');
        var nIndex = 0;
        var dotTimer = null;
        dotTimer = setInterval(function () {
            aDiv.eq(nIndex).show(50);
            nIndex++;
            if(nIndex==aDiv.length){
                clearInterval(dotTimer);
            }
        },50)
    });
    $('.trends-line').delay(1000).animate({
        width:'1920px',
        opacity:1
    },1000,function(){
        $('.trends-dot .dot14').addClass('hover');
    });
    //大球移入移出
    $('.trends-dot .ball').on('mouseover',function(){
        $(this).addClass('hover').siblings('div').removeClass('hover');
    });

    //优质代理人 代理人轮播信息
    $(document).ready(function(){
        if($('#rotate').length){
            $('#rotate').rotate({
                width:'100%',
                timer:5000,
                titlecolor:'#000000',
                itembgcolor:'#ffffff',
                infobgcolor:'#1a935c',
                buttonstyle:'white',
                bordercolor:'#d2d2d2'
            });
        }
    });
});

//公共组件效果
$(function(){  
    //个人中心侧边栏点击效果
    //点击
    $(".side01 a").on("click",function(){
        $(this).parent(".side01").next(".side-ol").toggle();
        $(this).parent(".side01").next(".side-ol").siblings(".side-ol").hide();
        $(this).parent(".side01").find("a").toggleClass("active01");
        //点击时添加样式
        $(this).addClass("active").parent(".side01").siblings(".side01").find("a").removeClass("active");
        $(this).parent(".side01").siblings(".side-ol").find("a").removeClass("active");
        //判断二级菜单是否显示
        if($(this).parent(".side01").next(".side-ol").is(":visible")){
            $(this).parent(".side01").next(".side-ol").find("a").removeClass("active");
        }
    })
    $(".side-ol a").on("click",function(){
        $(this).addClass("active").parent("li").siblings("li").find("a").removeClass("active"); 
        $(this).parents(".side-ol").siblings(".side01").find("a").removeClass("active");
        $(this).parents(".side-ol").siblings(".side-ol").find("a").removeClass("active");
    })

    //页面右边公共标题tab切换
    $(".label-nav").on("click","li",function(){
        $(this).children("a").addClass("onactive");
        $(this).siblings("li").children("a").removeClass("onactive");
        $(".label-panel").eq($(this).index()).addClass("active").siblings(".label-panel").removeClass("active");
        $(".label-p01").eq($(this).index()).addClass("active").siblings(".label-p01").removeClass("active");
        /*$(this).find("a").each(function(){
            if($(this).find("a").text)
        })*/
    })
    /*$(".label-nav").find("a").each(function(){
        if($(this).find("a").text().length==2){
          $(this).addClass("on");
        }else if($(this).find("a").text().length==3){
          $(this).addClass("on01");
        }
    })*/
    //  用户管理 我的客户与我的代理Tab切换
    $('.c-tab .tab-nav span').on('click',function(){
        $(this).addClass('active').siblings().removeClass('active');
        $('.r-content .tab-panel').eq($(this).index()).addClass('active').siblings().removeClass('active');
    });

    //我的订单select--个人中心和店铺下拉菜单
    $('.datalist .select,.client-data01 .select,.product-table .select,.product-table01 .select,.label-panel .select').on('mouseover',function(){
      $(this).find('span').addClass('active').next('ul').stop().slideDown(350);
    }).on('mouseout',function(){
      $(this).find('span').removeClass('active').next('ul').stop().slideUp(350);
    }).find('li').on('click',function(){
      var oUl = $(this).parents('ul');
      oUl.stop().hide().prev('span').text($(this).text());
      oUl.stop().hide().prev('span').attr("data-value",$(this).attr("data-value"));
      //loadList();
    });

    //页面加载完，判断文本域中数字个数--个人中心和店铺个人信息填写
    if($("people .area").length>0){
        var su01=$("#area").val().length;
        $("#havebeen").html(su01);
        $("#surplus").html(100-su01);
    }
    $(".people .area").focus(function(){
        interVal = setInterval(time,100);//文本域获取焦点时，启动定时器
    })
    $(".people .area").blur(function(){
        clearInterval(interVal);//文本域失去焦点时，关闭定时器
    })
    //用定时器实时监测数字个数
    var time = function () {
        var su=$(".people #area").val().length;
        if(su<=100){
            $(".people #havebeen").html(su);
            $(".people #surplus").html(100-su);
        }else if(su>100){
            $(".people #area").val($(".people #area").val().slice(0,100));
        }
    }
    var interVal = null;
    $(".people #area").keyup(function(){
        interVal = setInterval(time, 100);
    })
})
//个人中心效果
$(function(){
    //展示失败原因
    $(".basie-p .fail-cause").on("click",function(){
        var txtCause=$(this).parents(".basie-p04").siblings(".b-cause");
        if(!txtCause.hasClass("active")){
            $(this).text("收起原因");
            txtCause.addClass("active");
        }else{
            $(this).text("展开原因");
            txtCause.removeClass("active");
        }
    })
    //实名认证文本框效果
    $(".r-name01 input").focus(function(){
        $(this).addClass("on");
    })
    $(".r-name01 input").blur(function(){
        $(this).removeClass("on");
    })
    //个人中心--我的消息tab切换
    $(".my-ul").on("click","li",function(){
        var liindex=$(this).index();
        $(this).addcss("color","#1ec5eb").siblings("li").css("color","#000");
        $(".my-info>div").eq(liindex).show().siblings("div").hide();
    })
    //个人中心--我的消息查看详细内容
    $(".i-tr04").on("click",function(){
       $(this).attr("data","see");
        if($(this[attr="data"])){
            $(this).siblings(".i-tr02").find("a").css("font-weight","normal");
        };
        $(this).parents(".label-content").hide();
        $(this).parents(".label-content").siblings(".my-all").show();
    })
    
    //个人中心--我的消息从详细内容页返回
    $(".all-p01 a").on("click",function(){
        $(this).parents(".my-all").hide();
        $(this).parents(".my-all").siblings(".label-content").show();
    })

    //投资管理页面--选择产品
    $(".menu01 ul").on("click","li",function(){
        var menuindex=$(this).index();
        $(this).addClass("active").siblings("li").removeClass("active");
        $(".account-set").eq(menuindex).show().siblings("div").hide();
    })  
    $(".p2p-menu ul").on("click","li",function(){
        var menuindex=$(this).index();
        $(this).css("color","#1ec5eb").siblings("li").css("color","#000");
        $(".account-set01").eq(menuindex).show().siblings("div").hide();
    })
    
    //投资管理--私募基金取消样式
    $(".d-td07-1").hide();
    $(".payment01").on("click",function(){
        $(this).parents(".d-td07").siblings(".d-td06").css("color","#333");
        $(this).parents(".d-td07").siblings(".d-td06").html("取消订单");
        $(this).parents(".d-td07").hide();
        $(this).parents(".d-td07").siblings(".d-td07-1").show();
    })
    //投资管理--点击删除，当前一行全都删除
    $(".datalist .delete").on("click",function(){
        $(this).parents("tr").remove();
    })

    //合格投资认证个人上传图片--选择
    $('.upload-check span').click(function(){
      $(this).addClass("on").removeClass("off").siblings("span").addClass("off").removeClass("on");
      $(".ul-upload li").eq($(this).index()).addClass("on").removeClass("off").siblings("li").addClass("off").removeClass("on");
    });
    //合格投资认证个人上传图片问号提示框
    $(".upload-check span").find("b").on("mouseover",function(){
      $(this).find(".tips").addClass("active");
      $(this).addClass("active");
    }).on("mouseout",function(){
      $(this).find(".tips").removeClass("active");
      $(this).removeClass("active");
    })
})
//申请代理人效果
$(function(){
    //个人信息的文本框,文本域和店铺信息的文本框获取焦点和失去焦点样式
    $(".inqq,.shop-text,.people .area,.shop-input").addClass("on");
    $(".inqq,.shop-text,.people .area,.shop-input").focus(function(){
        $(this).removeClass("on"); 
        $(this).addClass("on01");
    })
    
    $(".inqq,.shop-text,.people .area,.shop-input").blur(function(){
        if($(this).val()==0){
            $(this).addClass("on");
            $(this).removeClass("on01");
        }else{
            $(this).removeClass("on");
            $(this).removeClass("on01");
        }
    })
    
    //代理人页面--选择代理类型切换效果
    $(".p-choice .choice").on("click",function(){
        $(this).addClass("a01").siblings("a").removeClass("a01");
        $(".p-up").eq($(this).index()).addClass("active").siblings().removeClass("active");
        var otherFirstUpload = $(".p-up.active").siblings(".p-up").find('.upload').eq(0);
        otherFirstUpload.find('img').attr('src','');
        otherFirstUpload.find('input').attr('path','').val('');
        otherFirstUpload.removeClass('done').nextAll('.upload').remove();
        if($(this).text()=="财富产品"){
            $(this).parents(".people").siblings(".shop").find(".s-radio2").show();
        }else{
            $(this).parents(".people").siblings(".shop").find(".s-radio2").hide();
        }
    })
    // 代理人提交信息的判断状态
    // $(".info-submit").on("click",function(){//首次提交，显示已经提交
    //    $(this).parents(".apply-info").hide();
    //    $(this).parents(".apply-info").siblings(".shop-success").show();
    // })
    $(".again-submit").on("click",function(){//若显示提交失败，点击重新申请到店铺申请页面
        $(this).parents(".shop-fail").hide();
        $(this).parents(".shop-fail").siblings(".shop-again").show();
    })
    // $(".shop-submit").on("click",function(){//再次提交，显示已经提交
    //    $(this).parents(".shop-again").hide();
    //    $(this).parents(".shop-again").siblings(".shop-success").show();
    // })

    //我申请的--当状态没有待答复时，没有提示信息
    $(".my-apply .m-td03").each(function(){
        var m_text=$(this).text();
        if(m_text=="待答复"){
            $(".label-p01 a").text("待答复的申请正在审核中，请耐心等待~");
        }else{
            $(".label-p01 a").text("");
        }
    });

    //我申请的--表格隔行变色
    $(".apply-tab tr:even,.menu-list tr:even,.menu-list01 tr:even,.menu-list02 tr:even,.menu-list03 tr:even,.menu-list04 tr:even,.menu-list05 tr:even").css("background","#f6f6f6");

    //  认证图片上传事件
        /*function uploadImg(obj){
            var oUpload = $(obj).parents('.upload');
            var oFile = oUpload.find('input[type="file"]')[0];
            var oImg = oUpload.find('img')[0];

            oUpload.removeClass('done');
            if(window.FileReader){//chrome,firefox7+,opera,IE10,IE9，IE9也可以用滤镜来实现
                oFReader = new FileReader();
                oFReader.readAsDataURL(oFile.files[0]);
                oFReader.onload = function (oFREvent) {
                    oImg.src = oFREvent.target.result;
                };  
            }else if(document.all){
                oFile.select();
                var reallocalpath = document.selection.createRange().text;//IE下获取实际的本地文件路径
                if (window.ie6) {
                    oImg.src = reallocalpath; //IE6浏览器设置img的src为本地路径可以直接显示图片
                }else{
                    //非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现，IE10浏览器不支持滤镜，需要用FileReader来实现，所以注意判断FileReader
                    oImg.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\"" + reallocalpath + "\")";
                    oImg.src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';
                    //  设置img的src为base64编码的透明图片，要不会显示红xx
                }
            }else if(oFile.files){//firefox6-
               if (oFile.files.item(0)) {
                    url = oFile.files.item(0).getAsDataURL();
                    oImg.src = url;
               }
            }
            //  上传完成后
            oUpload.addClass('done');
        } 

    var index = 0;*/
    //认证图片上传
    /*function fileChange(){
        $(this).parents(".upload").children(".u-b").find("a img").css({
            'display':'block'
        })
        $(this).parents(".upload").children(".u-b").css({
            'display':'block'
        })
        var fileId = $(this).attr('id');
        index++;
        fileId = fileId+(index+'');
        $(this).attr('id',fileId);
        if ($(this).val()=='') {
            return;
        }
        uploadImg(this);
        //  添加新的上传模块
        var aMultiple = $(this).parents('li.multiple');
        var aMultipleIndex = aMultiple.index();

        var aSultiple = $(this).parents('li.sultiple');
        var aSultipleIndex = aSultiple.index();

        var aSoltiple = $(this).parents('li.sultiple');
        var aSoltipleIndex = aSoltiple.index();      
        //合格投资人认证上传图片
        if(aMultiple.length>0){
            //  金融资产证明少于6张或个人收入证明少于12张时，添加新的上传模块
            if ((aMultipleIndex==0&&aMultiple.find('.upload').length<6)||(aMultipleIndex==1&&aMultiple.find('.upload').length<12)) {
                var oLi = $(this).parents('li');
                var oUpload = oLi.find('.upload').eq(0).clone(true);
                if (aMultiple.siblings('li').length==0) {
                    oUpload.find('.u-t').remove();
                }
                oUpload.removeClass('done').find('.file').attr('path','');
                oLi.append(oUpload);
                $('.server-error').text('').fadeOut();
            }else{
                //  金融资产证明或个人收入证明到达上传上限时，提示文字
                if (aMultipleIndex==0) {
                    $('.server-error').text('金融资产证明最多可上传6张，如未达到要求金额，其余资料可邮寄，地址请拨打400获取').fadeIn();
                }
                if (aMultipleIndex==1) {
                    $('.server-error').text('个人收入证明最多可上传12张，如未达到要求金额，其余资料可邮寄，地址请拨打400获取').fadeIn();
                }
            }
        }
        //申请代理人上传图片
        if(aSultiple.length>0,aSoltiple.length>0){
            //  职业证书少于6张且店铺头像只能上传一张，添加新的上传模块
            if ((aSultipleIndex==0&&aSultiple.find('.upload').length<4) || (aSultipleIndex==0&&aSultiple.find('.upload').length<5) || (aSoltipleIndex==1&&aSoltiple.find('.upload').length<1)) {
                var oLi = $(this).parents('li');
                var oUpload = oLi.find('.upload').eq(0).clone(true);
                if (aSultiple.siblings('li').length==0,aSoltiple.siblings('li').length==0) {
                    oUpload.find('.u-t').remove();
                }
                oUpload.removeClass('done').find('.file').attr('path','');
                oLi.append(oUpload);
                $('.server-error').text('').fadeOut();
            }
        }
        ajaxFileUpload(fileId);
    }
    $('.upload .file').on('change',fileChange);*/
    //  认证图片重新图片
    /*$('.upload .u-del').on('click',function(){
        var src=$(this).parents('.upload').removeClass('done').find('img').attr('src')
        $(this).parents('.upload').children(".u-b").find('a img').css("display",'none')
        $(this).parents('.upload').children(".u-b").css("display",'none')
        $(this).parents('.upload').find('input').attr('path','');
        if($(this).parents('li.multiple').find('.upload').length>1){
            $(this).parents('.upload').remove();
        }
        if($(this).parents('li.sultiple').find('.upload').length>1){
            $(this).parents('.upload').remove();
        }
        if($(this).parents('li.risktiple').find('.upload').length>1){
            $(this).parents('.upload').remove();
        }
        if($(this).parents('li.pertiple').find('.upload').length>1){
            $(this).parents('.upload').remove();
        }
    });*/
    //认证图片上传TAB切换
    $('.upload-tab span').on('click',function(){
        var nIndex = $(this).index();
        $(this).removeClass('off').addClass('on').siblings().removeClass('on').addClass('off');
        var curLi = $(this).parent().next('ul').find('li').eq(nIndex);
        var otherLi = curLi.siblings();
        curLi.removeClass('off').addClass('on');
        otherLi.removeClass('on').addClass('off').find('.upload').eq(0).removeClass('done').find('img').attr('src','');
        otherLi.find('.upload').eq(0).nextAll('.upload').remove();
    });
    //  风险评测题目选择
    $('.p-test dd').on('click',function(){
        if ($(this).parents('dl').hasClass('multiple')) {
            //  多选题时
            $(this).toggleClass('checked');
        }else{
            //  单选题时
            $(this).addClass('checked').siblings('dd').removeClass('checked');
        }
    });

    //  风险评测分数计算结果展示--世泽产品
    $('.p-test .submit').on('click',function(){
        var oTest = $(this).parents('.p-test');
        var aDl = oTest.find('dl');
        var aCheckedDl = aDl.has('.checked');
        var nScore = 0;
        var oResult = {};
        if (aDl.length != aCheckedDl.length) {
            //  有题目未选择答案
            alert('您还有题目没有选择答案！');
            return;
        }else{
            for (var i = 0; i < aCheckedDl.length; i++) {
                var aChecked = $(aCheckedDl[i]).find('dd.checked');
                //  多选题取分数最高的选项
                if (aChecked.length>1) {
                    var nMax = 0;
                    for (var j = 0; j < aChecked.length; j++) {
                        if (nMax<$(aChecked[j]).attr('data-n')) {
                            nMax = Number($(aChecked[j]).attr('data-n'));
                        }
                    }
                    nScore += nMax;
                }else{
                    //  单选题直接相加
                    nScore = nScore + Number(aChecked.eq(0).attr('data-n'));
                }
            }
        }
        //  根据分数划分等级
        var risk_level = '';
        if (nScore<40) {
            oResult.levelClass = 'level1';
            oResult.ability = '保守型';
            oResult.level = '较低';
            risk_level = '1';
        }else if(nScore>=40 && nScore<=75){
            oResult.levelClass = 'level2';
            oResult.ability = '稳健型';
            oResult.level = '一般';
            risk_level = '2';
        }else{
            oResult.levelClass = 'level3';
            oResult.ability = '积极型';
            oResult.level = '较高';
            risk_level = '3';
        }
        //  设置评测结果并保存
        $('#t-result .r-wrap').addClass(''+oResult.levelClass+'');
        $('#t-result .ability').html(oResult.ability);
        $('#t-result .level').html(oResult.level);
        $('.p-test').hide();
        $.ajax({
            type: 'POST',
            url: BASE_PATH+'/user/auth/commitRiskAssessment.htm?riskTypeId='+risk_level,
            dataType: 'JSON',
            success: function(data) {
                if(data.errorCode == '200'){

                }
            }
        });
        //  显示评测结果内容
        $('#t-result').show().find('.confirm').on('click',function(){
            $('#t-result').hide();
            $('.mask').remove();
        });
    });
    //  风险评测分数计算结果展示--文娱产品
    $('.p-test .ent-submit').on('click',function(){
        var oTest = $(this).parents('.p-test');
        var aDl = oTest.find('dl');
        var aCheckedDl = aDl.has('.checked');
        var nScore = 0;
        var oResult = {};
        if (aDl.length != aCheckedDl.length) {
            //  有题目未选择答案
            alert('您还有题目没有选择答案！');
            return;
        }else{
            for (var i = 0; i < aCheckedDl.length; i++) {
                var aChecked = $(aCheckedDl[i]).find('dd.checked');
                //  多选题取分数最高的选项
                if (aChecked.length>1) {
                    var nMax = 0;
                    for (var j = 0; j < aChecked.length; j++) {
                        if (nMax<$(aChecked[j]).attr('data-n')) {
                            nMax = Number($(aChecked[j]).attr('data-n'));
                        }
                    }
                    nScore += nMax;
                }else{
                    //  单选题直接相加
                    nScore = nScore + Number(aChecked.eq(0).attr('data-n'));
                }
            }
        }
        //  根据分数划分等级
        var risk_level = '';
        if (nScore<=10) {
            oResult.levelClass = 'level5';
            oResult.ability = '保本型';
            risk_level = '1';
        }else if(nScore>=11 && nScore<=35){
            oResult.levelClass = 'level1';
            oResult.ability = '保守型';
            risk_level = '2';
        }else if(nScore>=36 && nScore<=65){
            oResult.levelClass = 'level2';
            oResult.ability = '稳健型';
            risk_level = '3';
        }else if(nScore>=66 && nScore<=90){
            oResult.levelClass = 'level3';
            oResult.ability = '积极型';
            risk_level = '4';
        }else if(nScore>=91){
            oResult.levelClass = 'level4';
            oResult.ability = '进取型';
            risk_level = '5';
        }
        //  设置评测结果并保存
        $('#t-result .r-wrap').addClass(''+oResult.levelClass+'');
        $('#t-result .ability').html(oResult.ability);
        $('#t-result .level').html(oResult.level);
        $('.p-test').hide();
        $.ajax({
            type: 'POST',
            url: BASE_PATH+'/user/auth/commitRiskAssessment.htm?riskTypeId='+risk_level,
            dataType: 'JSON',
            success: function(data) {
                if(data.errorCode == '200'){

                }
            }
        });
        //  显示评测结果内容
        $('#t-result').show().find('.confirm').on('click',function(){
            $('#t-result').hide();
            $('.mask').remove();
        });
    });
    //  风险评测重新评测--文娱产品
    $(".J_test-again .again").on("click",function(){
            $('.ent-pest').show();
            $('#t-result').hide();
            $('dd').each(function(index){
                $(this).removeClass('checked');
            });
        });
    }) 
//P2P页面效果
$(function(){
    //绑定银行卡的文本框获取焦点和失去焦点样式
    $(".contact input,.bank input,.money-p2 input").focus(function(){
        $(this).addClass("active");
    })
    $(".contact input,.bank input,.money-p2 input").blur(function(){
        $(this).removeClass("active");
        $(this).css({"background":"#fff"});
        if(($(this).val()) == ''){
            $(this).css({"background":"#f9f9f9"});
        }
    })
    //绑定银行卡--选择市区县
    function stopFunc(e) {
        e.stopPropagation ? e.stopPropagation() : e.cancelBubble = true;
    }
    
    var city_arr=[];
    $(".cell-b").on("click",function(e){
            e = e || event; stopFunc(e);
            $(this).siblings(".p2p-select").toggle();
            if($(this).siblings(".p2p-select").is(':visible')){ 
                $(this).addClass("active01");
            }else{
                $(this).removeClass("active01");
                $(this).css({"background-color":"#fff"});
            }
            $(this).siblings(".p2p-select").find(".region").on("click","span",function(){
                $(this).addClass("span-bg").siblings("span").removeClass("span-bg");

                var title=$(this).parents(".region").attr("data-title");
               if(title=="province"){//点击城市
                    city_arr=[];
                    city_arr.push($(this).html());
               }else if(title=="city"){//市
                    if(city_arr.length!=1){
                         city_arr.pop();
                    }
                    city_arr[1]=$(this).html();
               }else{//选择区
                    if(city_arr.length!=2){
                        city_arr.pop();
                        return false;
                    }
                    city_arr[2]=$(this).html();
               }
               var str=''
               for(var i=0;i<city_arr.length;i++){
                if(city_arr[i]!=""){
                    str+='<span>'+city_arr[i]+'</span>';
                }
               }
               str=city_arr.join(",").replace(/\,/g,"/");
               $(this).parents(".p2p-select").siblings("#city_show").html("").append(str).css("color","#333");
               var index = $(this).parents('.region').index();
                if( index !=  $(this).parents('.p2p-content').find('.region').length -1){
                    $(this).parents('.region').removeClass('on').next('.region').addClass('on');
                    $(this).parents('.p2p-content').siblings('ul').find('li').eq(index).removeClass('on').next().addClass('on');
                }else{
                    $(this).parents('.p2p-select').hide();
                }
               
            })
            $(".p2p-select").find(".region").on("click",function(e){
                e = e || event; stopFunc(e);
            })
        })
    .siblings(".p2p-select").find("li").on("click",function(e){
            e = e || event; stopFunc(e);
            $(this).addClass("on").siblings("li").removeClass("on");
            $(this).parent("ul").siblings(".p2p-content").find(".region").eq($(this).index()).addClass("on")
            .siblings(".region").removeClass("on");   
    })
    $(document).on("click",function(e){  
        $(".p2p-select").hide();
        $(".p2p-select").siblings(".cell-b").removeClass("active01").css("background-color","#fff");   
    })
    //银行卡绑定弹层的跳转效果
    /*$(".cell-submit").on("click",function(){
        $(this).parents(".bind-bank").hide();
        $(this).parents(".bind-bank").next(".validate").show();
    })
    $(".v-submit").on("click",function(){
        $(this).parents(".validate").hide();
        $(this).parents(".validate").next(".bind-success").show();
    })*/
    $(".pro-rich").on("click",function(){
        $(this).parents(".bind-bank").hide();
        $(this).parents(".bind-bank").next(".p-rich").show();  
    })
    $(".rich-konw").on("click",function(){
        $(this).parents(".p-rich").hide();
        $(this).parents(".p-rich").prev(".bind-bank").show();  
    })

    //密码框的密码是否显示眼睛图标
    $(".cell-bd .sprites").on("click",function(){
        $(this).toggleClass("active");
        //查看密码是否显示
        if($(this).hasClass("active")){//当眼睛打开，密码框隐藏，文本框显示
            $(this).parents(".cell-bd").find(".mima_dd").hide();
            $(this).parents(".cell-bd").find(".mima_wz").show();
            $(this).parents(".cell-bd").children(".mima_wz").val($(this).parent(".cell-bd").children(".mima_dd").val());//让框内的隐藏文字变成相对应的显示的文字
        }else{//当眼睛打开，文本框隐藏，密码框显示
            $(this).parents(".cell-bd").children(".mima_dd").show();
            $(this).parents(".cell-bd").children(".mima_wz").hide();
            $(this).parents(".cell-bd").children(".mima_dd").val($(this).parent(".cell-bd").children(".mima_wz").val());//让框内的显示文字变成相对应的隐藏的文字
        }
    })
    //选择开户银行 
    $(".open p").on("click",function(){
        var openBank=$(this).find("img").clone(true);
        $(this).parents(".p2p-select").prev(".cell-b").text("").append(openBank).css("padding-top","2px").addClass("active01");
        $(this).parents(".p2p-select").hide();
    })

    //发送验证码效果
    /*$(".get-code01").on("click",function(){
        $(this).text("60S后重发");
        $(this).css("background","#c4c4c4");
        //60s倒计时发送验证码
        var i=60;
        var countdown = null;
        function timeShow(){
            i--;
            $(".get-code01").html(i + "S后重发");
            if(i<1){
                clearInterval(countdown);
                $(".get-code01").html("重新发送");
                $(".get-code01").css("background","#1ec5eb");
            }
        };
        countdown = setInterval(timeShow,1000);
    })*/

    //p2p产品详情页--进度条
    var _width = $('.bar-num').text();//获取百分比数值
    $('.bar-span02').css('width',_width+'%');//将数值赋值给main的width
}) 
//  打开popup弹窗
function openPopup(selector){
    $('.popup').hide();
    if($('.mask').length==0||$('.reg1-content').length>0){
        $('<div class="mask"></div>').appendTo($('body')).fadeIn();
    }
    $('#'+selector+'').fadeIn();

    //判断当前id后，2秒消失
    if(selector=='s-success'){
        setTimeout(function(){
            $('#'+selector+'').fadeOut();
            $('.mask').remove();
        },2000);
    }
}
//  产品详情
$(function(){
    
    //  全局参数
    var nMoneySum = nStart = 0,      //  产品起投金额
        nIncreases = 0,              //  产品递增金额
        nAmount = 0;                 //  产品募集上限


    //  产品详情设置操作参数
    if ($('#fund-detail .info').hasClass('on')){
        nMoneySum = nStart = parseFloat($('#fund-detail #startingLowAmount').val());
        nIncreases = parseFloat( delcommafy($('#fund-detail #chaseAmount').val() ) );
        nAmount = parseFloat($('#fund-detail #startingTopAmount').val()); 
    }

    //  产品详情认购金额增减按钮操作
    $('#fund-detail .amount strong').text($('.start').text());
    $('#fund-detail .reduce').addClass('disabled');    
    $('#fund-detail .num span').on('click',function(){
        if ($(this).parents('.info').hasClass('on')) {
            //  产品在募集状态时，执行操作
            if ($(this).hasClass('plus')){
                //  增加认购金额
                $('#fund-detail .reduce').removeClass('disabled');
                nMoneySum += nIncreases;
                if(nMoneySum==nAmount){
                    $('#fund-detail .plus').addClass('disabled');
                }
                if(nMoneySum>nAmount){
                    nMoneySum -= nIncreases;
                    $('#fund-detail .plus').addClass('disabled');
                }
            }else{
                //  减少认购金额
                $('#fund-detail .plus').removeClass('disabled');
                nMoneySum -= nIncreases;
                if(nMoneySum<=nStart){
                    nMoneySum = nStart;
                    $('#fund-detail .reduce').addClass('disabled');
                }
            }
            //  设置隐藏域的认购金额
            $('#fund-detail #amount').val(parseFloat(nMoneySum).toFixed(2));
            //  格式化显示认购金额
            $('#fund-detail .amount strong').text(outputmoney(nMoneySum));
            //  设置滑块位置
            $('#fund-detail .num-slider').slider('value',nMoneySum);
            //  到达上限时，不可加
            if(nMoneySum>nAmount-nIncreases){
                $('#fund-detail .plus').addClass('disabled');
            }
        }else{
            //  产品在非募集状态时，不执行操作
            return false;
        }
    });

    //  去除千分位
    function delcommafy(num){
       if((num+'').trim() == ''){
          return '';
       }

       num = num.replace(/,/gi,'');
       return num;
    }

    //  格式化金额数字
    function outputmoney(number) {
        if(isNaN(number) || number == "")return "";
        number = Math.round(number * 100) / 100;
            if (number < 0)
                return '-' + outputdollars(Math.floor(Math.abs(number) - 0) + '') + outputcents(Math.abs(number) - 0);
            else
                return outputdollars(Math.floor(number - 0) + '') + outputcents(number - 0);
        } 
    function outputdollars(number) {
        if (number.length <= 3)
            return (number == '' ? '0' : number);
        else {
            var mod = number.length % 3;
            var output = (mod == 0 ? '' : (number.substring(0, mod)));
            for (i = 0; i < Math.floor(number.length / 3); i++) {
                if ((mod == 0) && (i == 0))
                    output += number.substring(mod + 3 * i, mod + 3 * i + 3);
                else
                    output += ',' + number.substring(mod + 3 * i, mod + 3 * i + 3);
            }
            return (output);
        }
    }
    function outputcents(amount) {
        amount = Math.round(((amount) - Math.floor(amount)) * 100);
        return (amount < 10 ? '.0' + amount : '.' + amount);
    }

    //  产品详情认购金额拖拽滑块操作
    if($('#fund-detail .num-slider').length>0){
        $('#fund-detail .num-slider').slider({
          range: "min",
          value: nStart,
          min: nStart,
          max: nAmount,
          step: nIncreases,
          slide: function(event,ui){
            nMoneySum = ui.value;
            //  设置隐藏域的认购金额
            $('#fund-detail #amount').val(parseFloat(nMoneySum).toFixed(2));
            //  格式化显示认购金额
            $('#fund-detail .amount strong').text(outputmoney(nMoneySum));
            //  根据滑块数值更改加减按钮状态
            if(nMoneySum==nAmount){
                $('#fund-detail .reduce').removeClass('disabled');
                $('#fund-detail .plus').addClass('disabled');
            }else if(nMoneySum==nStart){
                $('#fund-detail .reduce').addClass('disabled');
                $('#fund-detail .plus').removeClass('disabled');
            }else{
                $('#fund-detail .reduce').removeClass('disabled');
                $('#fund-detail .plus').removeClass('disabled');
            }
          }
        });
        //  当起投金额等于认购最大金额时，加减按钮置灰，滑块置最大值
        if (nStart==nAmount){
            $('#fund-detail .ui-slider-range').css('width','100%');
            $('#fund-detail .ui-slider-handle').css('left','100%');
            $('#fund-detail .reduce,#fund-detail .plus').addClass('disabled').off('click');
        }
    }
    //  产品详情与募集说明书Tab切换
    $('.p-tab .tab-nav span,.p2p-explain .tab-nav span').on('click',function(){
        $(this).addClass('active').siblings().removeClass('active');
        $('.p-tab .tab-panel,.p2p-explain .tab-panel').eq($(this).index()).addClass('active').siblings().removeClass('active');
    });

    //  产品详情表格各行变色
    $('.p-tab table tr:even').addClass('even');

    //  关闭popup弹窗
    $('.popup .close,.p-doc a').on('click',function(){
        $(this).parent('.popup').hide();
        $('.mask').remove();
    });
    //  关闭people弹窗 申请更多售卖权限
    $('.people .close').on('click',function(){
        $('.s-sale').hide();
        $('.mask').remove();
    });
    //  关闭s-prompt弹窗
    $('.s-prompt .close').on('click',function(){
        $(this).parent('.s-prompt').hide();
        $('.mask').remove();
    });
    //  关闭s-settle弹窗
    $('.s-settle .s-know').on('click',function(){
        $(this).parents('.s-settle').hide();
        $('.mask').remove();
    });
    //  关闭s-see弹窗
    $('.s-see .btn-blue').on('click',function(){
        $('.s-see').hide();
        $('.mask').remove();
    });
    //  查看注意事项
    function showNotice(selector){
        if($('.mask').length==0){
            $('<div class="mask"></div>').appendTo($('body')).fadeIn();
        }else{
            $('.mask').addClass('heigher').fadeIn();
        }
        $('#'+selector+'').show();
    }
    //  查看个人注意事项
    $('#p-test .t-notice').on('click',function(){
        showNotice('p-notice');
    });
    //  查看企业注意事项
    $('#c-test .t-notice').on('click',function(){
        showNotice('c-notice');
    });
    //  关闭注意事项
    $('.p-notice a,.c-notice a,.validate .v-close,.bind-success .s-close,.bind-bank .b-close,.poup-auth01 .a-close,.r-name01 .a-close,.r-name .a-close').on('click',function(){
        if($('.popup:visible').length>1){
            $(this).parents('.popup').fadeOut();
            $('.mask').removeClass('heigher');
        }else{
            $(this).parents('.popup').hide();
            $('.mask').remove();
        }
    });

    //  打开文档弹窗(注册协议、风险揭示书、协作合同、投资承诺书)
    $('.s-reg-doc,.s-risk-doc,.s-contract-doc,.s-pledge-doc,.s-delist-doc').on('click',function(){
        if ($(this).hasClass('s-contract-doc')) {
            var title = $(this).text();
            var url = $(this).attr('href');
            $.ajax({
                url: url, 
                type: "GET", 
                success: function(data){
                    $('#contract-doc .txt').html(data);
                }
            });
            $('#contract-doc h3').html(title.substring(1,title.length-1));
        }
        openPopup($(this).attr('class').substr(2));
        return false;
    });
   
    //  同意协议选项
    $('.agree .i-radio').on('click',function(){
        $(this).toggleClass('checked');
        var oHidden = $(this).nextAll('input[type="hidden"]');
        if (oHidden.length>0) {
            if (oHidden.val()=='') {
                oHidden.val('1')
            }else{
                oHidden.val('');
            }
        }
    });

    //  实名认证TAB切换
    $('.auth-tab a').on('click',function(){
        var nIndex = $(this).index();
        $(this).addClass('active').siblings().removeClass('active');
        $(this).parent().next('ul').find('li').eq(nIndex).addClass('on').siblings().removeClass('on');
    });

    //  页面内容加载完成后设置滚动条
    $(window).on('load',function(){
        var mCustomScrollbar = window.mCustomScrollbar;
        if($('.txt').length>0 && mCustomScrollbar){
            $('.txt').mCustomScrollbar();
        }
        if($('.article').length>0 && mCustomScrollbar){
            $('.article').mCustomScrollbar();
        }
        if($('.popup .form').length>0 && mCustomScrollbar){
            $('.popup .form').mCustomScrollbar();
        }
        if($('.real-scroll').length>0 && mCustomScrollbar){
            $('.real-scroll').mCustomScrollbar();
        }
        if($(".t-protocol .agreement").length>0 && mCustomScrollbar){
            $('.t-protocol .agreement').mCustomScrollbar();
        }
    }); 
    //store 模块下table的隔行变色
    $(".product-table tr:even,.product-table01 tr:even,.table-data tr:even,.table-data01 tr:even,.settle-data tr:even,.settle-data01 tr:even,.settle-data02 tr:even,.client-data tr:even,.client-data01 tr:even,.agent-data tr:even,.agent-data01 tr:even").css("background","#f6f6f6");
    $(".details-data tr:even").css("background","#f6f6f6");
    $(".see-data tr:even").css("background","#f6f6f6");
    //  店铺管理模块 侧边栏Tab切换
    $('.sidebar .side02 a').on('click',function(){
        $(this).addClass("active").parent(".side02").siblings(".side02").find("a").removeClass("active");
    });

    //  产品推荐标题栏 产品筛选
    /*$('.sl-wrap .sl-value a').on('click',function(){
        $(this).addClass('active').siblings().removeClass('active');
        if($(this).text().indexOf('P2P')>=0){
            $('.sl-p2p').hide();
            $('.sl-wrap.private').show();
        }else{
            $('.sl-p2p').show();
            $('.sl-wrap.private').hide();
        }
    });*/

    //  input标签placeholder属性兼容处理
    /*$('input[placeholder],textarea[placeholder]').each(function(){
            var that = $(this),   
            text= that.attr('placeholder');   
            if(that.attr('type')!='password'){
                that.attr('placeholder','');
            }
        if(that.val()===''&&that.attr('type')!='password'){
                that.val(text).addClass('placeholder');   
            }
            that.focus(function(){
                if(that.val()===text&&that.attr('type')!='password'){
                    that.val('').removeClass('placeholder');
                }  
            })   
            .blur(function(){   
                if(that.val()===''&&that.attr('type')!='password'){
                    that.val(text).addClass('placeholder');   
                }   
            })
            .closest('form').submit(function(){   
            if(that.val() === text&&that.attr('type')!='password'){
                    that.val('');   
                }   
            });   
        if(that.attr('type')=='password'){
            that.focus(function(){
                if(that.val()===''){
                    that.attr('placeholder','');
                }
            }).blur(function(){
                if(that.val()===''){
                    that.attr('placeholder',text);
                }
        });
    }

    });*/

    // 店铺管理顶部头像、店铺页头像、面包客列表头部头像   图片大小居中
    $('img.p-img').each(function () {
        var currentWidth = $(this).width();
        var parentWidth = $(this).closest('div').width();
        if(currentWidth<=parentWidth){
            $(this).css('margin-left','0');
        }

    });
    //  同意协议选项--确认函
    $('.s-affirm .i-radio').on('click',function(){
        $(this).toggleClass('checked');
        var oHidden = $(this).nextAll('input[type="hidden"]');
        if (oHidden.length>0) {
            if (oHidden.val()=='') {
                oHidden.val('1')
            }else{
                oHidden.val('');
            }
        }
    });
    //  叉号关闭确认函弹窗
    $('.s-affirm .close').on('click',function(){
        $('.s-affirm').hide();
        //判断是否有申请二级面包客弹窗
        if(!$(this).parents('.s-affirm').siblings().hasClass('s-agent')){
            $('.mask').remove();
        }
    });
    // 确定关闭确认函弹窗
    // $('.s-affirm .sa-btn').on('click',function(){
    //     $('.s-affirm').hide();
    //     $('.mask').remove();
    // });

    // store08 申请二级面包客
   /* $('.agent-list .list').on('click',function(){
        $('.s-agent').show();
        $('.mask').show();
    });*/
    // store08 弹窗 提交按钮
    $('.s-agent .btn-blue').on('click', function () {
        $('.s-agent01').show();
        $('.s-agent').hide();
    })
    //  store08 关闭s-agent弹窗
    $('.s-agent .close').on('click',function(){
        $('.s-agent').hide();
        $('.mask').remove();
    });
    //  store08  确定关闭s-agent弹窗
    $('.s-agent .btn02').on('click',function(){
        $('.s-agent').hide();
        $('.mask').remove();
    });
    // store08 取消
    $('.s-agent .btn01').on('click',function(){
        $(this).parents('.s-agent').hide();
        $('.mask').remove();
    });
    //  store08 关闭s-agent弹窗
    $('.s-agent01 .close').on('click',function(){
        $('.s-agent01').hide();
        $('.mask').remove();
    });
    //  store08  确定关闭s-agent弹窗
    $('.s-agent01 .btn02').on('click',function(){
        $('.s-agent01').hide();
        $('.mask').remove();
    });

    //提醒立即关注列表弹层--关闭弹窗
    $('.followbar .close').on('click',function(){
        $(this).parents('.followbar').hide();
        $('.mask').remove();
        $("input[name='keyword']").val("");
    });
    //点击立即关注提示关注成功，3秒后自动消失
 /*   $(".f-list .follow-close").on("click",function(){
        $(this).each(function(){
            $(this).parents(".followbar").hide();
            $(this).parents(".followbar").siblings(".s-success").show();
            setTimeout(function(){
                $(".f-list .follow-close").parents(".followbar").siblings(".s-success").hide();
                $('.mask').remove();
            },3000);
})
    })*/
    /* 面包客与店铺搜索模块 */
    var search = {
        oInput : $('.searchbar input'),
        oList : $('.search-datalist'),
        oClear : $('.searchbar .clear'),
        focus : function(){
            if(!search.oInput.hasClass('placeholder')&&search.oInput.val().length!=0){
                var str = search.oInput.val().toString();
                str = str.replace(/</g,'&lt;').replace(/>/g,'&gt;');
                search.oList.find('li').eq(0).html('搜“'+ str +'”相关的面包客');
                search.oList.find('li').eq(1).html('搜“'+ str +'”相关的店铺名');
                search.oList.fadeIn(200);
                search.oClear.show();
            }
        },
        keyup : function(){
            search.focus();
            if(!search.oInput.hasClass('placeholder')&&search.oInput.val().length==0){
                var str = search.oInput.val().toString();
                str = str.replace(/</g,'&lt;').replace(/>/g,'&gt;');
                search.oList.find('li').eq(0).html(str);
                search.oList.find('li').eq(1).html(str);
                search.oList.fadeOut(200);
                search.oClear.hide();
            }
        },
        blur : function(){
            search.oList.fadeOut(200);
            if(!search.oInput.hasClass('placeholder')&&search.oInput.val().length==0){
                search.oClear.hide();
            }
        },
        clear : function(){
            search.oClear.hide();
            search.oInput.val('').focus();
            search.oList.fadeOut(200);
        },
        init : function(){
            if(search.oInput.length==0){
                return;
            }
            if(!search.oInput.hasClass('placeholder')&&search.oInput.val().length!=0){
                search.oClear.show();
            }
            search.oInput.on('focus',search.focus).on('keyup',search.keyup).on('blur',search.blur);
            search.oClear.on('click',search.clear);
        }
    }
    search.init();

    $('.c-choice span').on('click',function(){
        $(this).addClass('checked').siblings('span').removeClass('checked');
    });
    //文娱产品中心--合格投资人签署弹层
    $(".s-ent-reveal").on("click",function(){
        showNotice('s-ent-reveal');
    });
    $(".s-ent-register").on("click",function(){
        showNotice('s-ent-deal');
    });
    $(".ent-risk-doc").on("click",function(){
        showNotice('risk-doc');
    });
    $(".ent-pledge-doc").on("click",function(){
        showNotice('pledge-doc');
    });
    $(".ent-delist-doc").on("click",function(){
        showNotice('delist-doc');
    });

//加频道后tab标签加滚动条
    //tab标签的样式调整
    $(".tab-layer a").each(function(){
        if($(this).text().length==2){
          $(this).addClass("on");
        }else if($(this).text().length==3){
          $(this).addClass("on01");
        }
    })
 
    $(".table-nav .tab").on("click", function () {
        $(this).addClass("active").siblings().removeClass("active");
    });
    //  计算tab容器宽度
    $(".tab-layer").width(function () {
        var widthSum = 0;
        $(".tab-layer .tab").each(function () {
            widthSum = widthSum + parseInt($(this).outerWidth(true));
        });
        return widthSum;
    });

    //生活服务收起展开
    $('.sl-more').on('click',function(){
        if(!$(this).hasClass('disabled')){
            $(this).addClass('disabled');
            $(this).addClass('on');
            $(this).text('收起');
            $(this).siblings('.sl-sort').removeClass('disabled');
        }else{
            $(this).removeClass('disabled');
            $(this).removeClass('on');
            $(this).text('更多');
            $(this).siblings('.sl-sort').addClass('disabled');
        }
    });

    //刷新页面，判断生活服务是否折行收起
    /*$(window).on('load',function(){
        $('.sl-sort').each(function(){
             var slHeight=$('.sl-sort').height();
             console.log(slHeight);
             if(slHeight!==50){
                  $('.sl-sort').addClass('disabled');
                  $('.disabled').siblings('.sl-more').show();
             }else{
                  $('.sl-sort').removeClass('disabled');
                  $('.sl-sort').siblings('.sl-more').hide();
             } 
        });
    })*/

    //生活服务产品切换样式
    $('.sl-wrap .sl-value a').on('click',function(){
        $(this).addClass('active').siblings('a').removeClass('active');
        $(this).parent('.sl-sort').siblings('a.sl-all').removeClass('active');
        $(this).siblings('.sl-sort').find('a.active').removeClass('active');
    })

    //关注面包客提示弹层
    $('#hint-attention .h-close,#hint-attention .sub-agent').on("click",function(){
        $(this).parents('#hint-attention').hide();

        if($(this).parents('#hint-attention').siblings().hasClass('.followbar')){
            if($(this).parents('#hint-attention').siblings(".followbar").show()){
                $(".mask").show();
            }
        }else{
            $('.mask').remove();
        }
    })


    
})

    //  重置alert事件
    window.alert = function(txt,callback){
        if($('.alert').length>0){
            return;
        }
        txt = txt.toString().replace(/<[^<>]+?>/g,'');
        var oAlert = $('<div class="alert">'
        +'<div class="a-head">提示</div>'
        +'<div class="a-body">'
        +'<p>'+txt+'</p>'
        +'<a href="javascript:;">确认</a>'
        +'</div>'
        +'</div>');
        var oAlertMask = $('<div class="alert-mask"></div>');
        $('body').append(oAlertMask,oAlert);
        //  设置显示位置，绑定按钮事件
        var nWidth = oAlert.outerWidth();
        var nHeight= oAlert.outerHeight();
        oAlert.css({'margin-top':-nHeight/2+'px','margin-left':-nWidth/2+'px'}).find('a').on('click',function(){
            $('.alert-mask,.alert').remove();
            callback&&callback();   
        });
        //  显示提示
        $('.alert-mask,.alert').fadeIn();
    };

    //  重置confirm事件
    function showConfirm(msg,doOk,doCancel){
       if($('#confirm').length>0){
           return;
       }
       var oConfirm = $('<div id="confirm">'
       +'<div class="c-head">提示</div>'
       +'<div class="c-body">'
       +'<p>'+msg+'</p>'
       +'<a class="ok" href="javascript:;">确定</a>'
       +'<a class="cancel" href="javascript:;">取消</a>'
       +'</div>'
       +'</div>');
       var oComfirmMask = $('<div class="confirm-mask"></div>');
       $('body').append(oComfirmMask,oConfirm);
       //  设置显示位置，绑定按钮事件
       var nWidth = oConfirm.outerWidth();
       var nHeight= oConfirm.outerHeight();
       oConfirm.css({'margin-top':-nHeight/2+'px','margin-left':-nWidth/2+'px'}).find('a').on('click',function(){
           if($(this).hasClass('ok')){
               doOk&&doOk();
           }else{
               doCancel&&doCancel();
           }
           $('.confirm-mask,#confirm').remove();
       });
       //  显示提示
       $('.confirm-mask,#confirm').fadeIn();
    };

    //  平台跳转提示
    function showSkip(type){
        var str = '';
        if(type=='1'){
            str = '正在前往世泽资本';
        }else if(type=='2'){
            str = '正在前往汇中网';
        }else{
            str = '正在前往';
        }
        var skip = '<section class="skip"><span>'+str+'</span></section>';
        var skipMask = $('<div class="skip-mask"></div>');
        $('body').append(skip,skipMask);
    }
    function blankOpen(url) {
        var blankFormStr = '<form id="blankForm" method="post" target="_blank" action="'+url+'"></form>';
        $("body").append(blankFormStr);
        var blankForm = $("#blankForm");
        blankForm.submit();
        blankForm.remove();
    }
    //跳转到三方平台的中转页
    function goDeputy(productType,url,sleepTime,isBlank) {
        if(isBlank){
            blankOpen(url);
        }else {
            if (!sleepTime) {
                sleepTime = 5000;
            }
            showSkip(productType);
            setTimeout(function () {
                window.location.href = url;
            }, sleepTime);
        }
    }

$(function () {

    /*产品筛选 Start*/

    //金融调用
    $('.J_finance .J_classify a').on('click', function () {
        classifyTab(this, '.J_finance .J_prop');
    });
    $('.J_finance .J_prop a').on('click', function () {
        selectedAttr(this);
    });
    //文娱调用
    $('.J_civic .J_classify a').on('click', function () {
        classifyTab(this, '.J_civic .J_prop');
    });
    $('.J_civic .J_prop a').on('click', function () {
        selectedAttr(this);
    });

    /*
     * 分类切换
     *   currentThis:当前点击元素
     *   obj: 要隐藏的元素
     * */
    function classifyTab(currentThis, obj) {
        $(currentThis).siblings().removeClass('active');
        $(currentThis).addClass('active');
        $(obj).hide();
        $(obj).eq($(currentThis).index()).show();
    }

    /*
     * 筛选条件
     * currentThis:当前点击元素
     * */
    function selectedAttr(currentThis) {
        $(currentThis).siblings().removeClass('active');
        $(currentThis).addClass('active');
    }

    /*产品筛选 End*/



    /*有娱详情滑块 Start*/
    /**
     * Created by wyunfei on 2017/5/19.
     */

    //总价
    var totalPrice = $('#J_total-price').val();
    //总份数
    var totalNum = $('#J_total-num').val();
    //限购份数
    var restrictBuy = $('#J_restrict-buy').val();
    //已售份数
    var slodOutNum = $('#J_slodOut-num').val();
    //剩余份数
    var surplus = totalNum - slodOutNum;
    //单价
    var singlePrice = totalPrice / totalNum;
    //滑动范围
    var range = $('#J_range').width();

    //计数
    var iNum = 1;
    var mayNum = 0;
    if (surplus < restrictBuy) {
        mayNum = surplus;
    } else {
        mayNum = restrictBuy;
    }

    //初始化单价
    $('#ent-detail .amount strong').text(outputmoney(singlePrice*iNum));

    $('#ent-detail .reduce').addClass('disabled');    
    $('#ent-detail .num span').on('click',function(){
        if ($(this).parents('.info').hasClass('on')) {
            //  产品在可操作状态时，执行操作
            if ($(this).hasClass('plus')){
                //  增加
                $('#ent-detail .reduce').removeClass('disabled');
                iNum++;
                if(iNum>=mayNum){
                    iNum=mayNum;
                    $('#ent-detail .plus').addClass('disabled');
                }
            }else{
                //  减少
                $('#ent-detail .plus').removeClass('disabled');
                iNum--;
                if(iNum<=1){
                    iNum = 1;
                    $('#ent-detail .reduce').addClass('disabled');
                }
            }
            //  设置隐藏域的金额
            $('#ent-detail #amount').val(parseFloat(singlePrice*iNum).toFixed(2));
            //  格式化显示金额
            $('#ent-detail .amount strong').text(outputmoney(singlePrice*iNum));
            //  设置滑块位置
            $('#ent-detail .num-slider').slider('value',iNum);
            //  到达上限时，不可加
            if(iNum>=mayNum){
                $('#ent-detail .plus').addClass('disabled');
            }
        }else{
            //  产品在不可操作状态时，不执行操作
            return false;
        }
    });

    //  格式化金额数字
    function outputmoney(number) {
        if(isNaN(number) || number == "")return "";
        number = Math.round(number * 100) / 100;
            if (number < 0)
                return '-' + outputdollars(Math.floor(Math.abs(number) - 0) + '') + outputcents(Math.abs(number) - 0);
            else
                return outputdollars(Math.floor(number - 0) + '') + outputcents(number - 0);
        } 
    function outputdollars(number) {
        if (number.length <= 3)
            return (number == '' ? '0' : number);
        else {
            var mod = number.length % 3;
            var output = (mod == 0 ? '' : (number.substring(0, mod)));
            for (i = 0; i < Math.floor(number.length / 3); i++) {
                if ((mod == 0) && (i == 0))
                    output += number.substring(mod + 3 * i, mod + 3 * i + 3);
                else
                    output += ',' + number.substring(mod + 3 * i, mod + 3 * i + 3);
            }
            return (output);
        }
    }
    function outputcents(amount) {
        amount = Math.round(((amount) - Math.floor(amount)) * 100);
        return (amount < 10 ? '.0' + amount : '.' + amount);
    }

    //  产品详情金额拖拽滑块操作
    if($('#ent-detail .num-slider').length>0){
        $('#ent-detail .num-slider').slider({
          range: 'min',
          value: iNum,
          min: 1,
          max: mayNum,
          step: 1,
          slide: function(event,ui){
            iNum = ui.value;
            //  设置隐藏域的金额
            $('#ent-detail #amount').val(parseFloat(singlePrice*iNum).toFixed(2));
            //  格式化显示金额
            $('#ent-detail .amount strong').text(outputmoney(singlePrice*iNum));
            //  根据滑块数值更改加减按钮状态
            if(iNum==mayNum){
                $('#ent-detail .reduce').removeClass('disabled');
                $('#ent-detail .plus').addClass('disabled');
            }else if(iNum==1){
                $('#ent-detail .reduce').addClass('disabled');
                $('#ent-detail .plus').removeClass('disabled');
            }else{
                $('#ent-detail .reduce').removeClass('disabled');
                $('#ent-detail .plus').removeClass('disabled');
            }
          }
        });
        //  到达上限时，加减按钮置灰，滑块置最大值
        if (mayNum==restrictBuy){
            $('#ent-detail .ui-slider-range').css('width','100%');
            $('#ent-detail .ui-slider-handle').css('left','100%');
            $('#ent-detail .reduce,#ent-detail .plus').addClass('disabled').off('click');
        }
    }

    /*有娱详情滑块 End*/

});

/*倒计时 Start*/
function getTime(time) {
    var timer = null;
    var endTime = time;
    var nowTime = new Date();
    var t = endTime - nowTime.getTime();
    var d = 0;
    var h = 0;
    var m = 0;
    var s = 0;
    if(t >= 0) {
        d = Math.floor(t / 1000 / 60 / 60 / 24);
        h = Math.floor(t / 1000 / 60 / 60 % 24);
        m = Math.floor(t / 1000 / 60 % 60);
        s = Math.floor(t / 1000 % 60);
    }

    var h0 = document.getElementById('h-0');
    var h1 = document.getElementById('h-1');
    var m0 = document.getElementById('m-0');
    var m1 = document.getElementById('m-1');
    var s0 = document.getElementById('s-0');
    var s1 = document.getElementById('s-1');
    var hSplit = complate(h+(d*24)).split('');
    var mSplit = complate(m).split('');
    var sSplit = complate(s).split('');

    h0.innerHTML = hSplit[0];
    h1.innerHTML = hSplit[1];
    m0.innerHTML = mSplit[0];
    m1.innerHTML = mSplit[1];
    s0.innerHTML = sSplit[0];
    s1.innerHTML = sSplit[1];

    if(h == 0 && m == 0 && s == 0) {
        clearInterval(timer);
        var html = "";
        html+= '<a class="btn" href="javascript:submitDwy();" id="submit_zx">咨询</a>';
        $("#snappedUp").html(html);
    }

};


var startTime = $("#startTime").val();
if(startTime){
    startTime = startTime.replace(new RegExp("-","gm"),"/");
    var starttimeHaoMiao = (new Date(startTime)).getTime(); //得到毫秒数
    if(new Date().getTime()<starttimeHaoMiao){
        timer = setInterval('getTime(' + starttimeHaoMiao + ')', 1000);
    }
}

/*倒计时 End*/
$(function(){
    


    function scoreResult(currentThis, objResult, num) {//num为1时分为5种级别，为2时分为3种级别
        var oTest = currentThis.parents('.p-test');
        var aDl = oTest.find('dl');
        var aCheckedDl = aDl.has('.checked');
        var nScore = 0;
        var oResult = {};
        if (aDl.length != aCheckedDl.length) {
            //  有题目未选择答案
            alert('您还有题目没有选择答案！');
            return;
        } else if (aDl.length == aCheckedDl.length) {
            for (var i = 0; i < aCheckedDl.length; i++) {
                var aChecked = $(aCheckedDl[i]).find('dd.checked');
                //  多选题取分数最高的选项
                if (aChecked.length > 1) {
                    var nMax = 0;
                    for (var j = 0; j < aChecked.length; j++) {
                        if (nMax < $(aChecked[j]).attr('data-n')) {
                            nMax = Number($(aChecked[j]).attr('data-n'));
                        }
                    }
                    nScore += nMax;
                } else {
                    //  单选题直接相加
                    nScore = nScore + Number(aChecked.eq(0).attr('data-n'));
                }
            }

        }
        var num = num;
        //  根据分数划分等级

        isRanking(nScore, oResult, objResult, num);

    }


    /*
     * 判定级别种类
     * @param<String>nScore
     * @param<String>oResult
     * @param<String>objResult
     * @param<Number>num
     */

    function isRanking(nScore, oResult, objResult, num) {//  根据分数划分等级

        var risk_level = '';
        //
        if (num == '1') {
            if (nScore < 40) {
                oResult.levelClass = 'level1';
                oResult.ability = '保守型';
                oResult.level = '较低';
                risk_level = '1';
            } else if (nScore >= 40 && nScore <= 75) {
                oResult.levelClass = 'level2';
                oResult.ability = '稳健型';
                oResult.level = '一般';
                risk_level = '2';
            } else {
                oResult.levelClass = 'level3';
                oResult.ability = '积极型';
                oResult.level = '较高';
                risk_level = '3';
            }
        } else if (num == '2') {
            if (nScore <= 10) {
                oResult.levelClass = 'level5';
                oResult.ability = '保本型';
                risk_level = '1';
            } else if (nScore >= 11 && nScore <= 35) {
                oResult.levelClass = 'level1';
                oResult.ability = '保守型';
                risk_level = '2';
            } else if (nScore >= 36 && nScore <= 65) {
                oResult.levelClass = 'level2';
                oResult.ability = '稳健型';
                risk_level = '3';
            } else if (nScore >= 66 && nScore <= 90) {
                oResult.levelClass = 'level3';
                oResult.ability = '积极型';
                risk_level = '4';
            } else if (nScore >= 91) {
                oResult.levelClass = 'level4';
                oResult.ability = '进取型';
                risk_level = '5';
            }
        }
        $('.result-ok, .result-sorry').hide();
        $('.r-wrap i').hide();
        if (nScore >= 66) {
            $(objResult + ' ' + '.result-ok').show();
            $(objResult + ' ' + '.confirm').text('确定');
        } else {
            $(objResult + ' ' + '.result-sorry').show();
            $(objResult + ' ' + '.confirm').text('关闭');
            $(objResult + ' ' + '.r-wrap i').css('display', 'inline-block');
        }
        //nScore >= 66 ? $(objResult + ' ' + '.result-ok').show() : $(objResult + ' ' + '.result-sorry').show();
        //nScore < 66 ? $(objResult + ' ' + '.r-wrap i').css('display', 'inline-block') : '';
        //  设置评测结果并保存
        $(objResult + ' ' + '.r-wrap').addClass('' + oResult.levelClass + '');
        console.log($(objResult + ' ' + '.r-wrap').attr('class'));
        $(objResult + ' ' + '.r-wrap').removeClass($(objResult + ' ' + '.r-wrap').eq(1).attr('class'))
        $(objResult + ' ' + '.ability').html(oResult.ability);
        $(objResult + ' ' + '.level').html(oResult.level);
        $('.p-test').hide();
        $(objResult).show();
        $.ajax({
            type: 'POST',
            url: BASE_PATH + '/user/dwy/commitRiskAssessment.htm?score=' + nScore,
            dataType: 'JSON',
            success: function (data) {
                if (data.errorCode != '200') {
    //
                }
            }
        });

        $(objResult).show().find('.confirm').on('click', function () {
            $(objResult).hide();
            $('.mask').remove();
        });
    }

    /*
     * 重新评测
     * @param<String>objHide
     * @param<String>objShow
     */
    function testAgain(objHide, objShow) {//重新评测
        var _class = $(objHide + ' ' + '.r-wrap').attr('class').split(' ')[1]
        $(objHide + ' ' + '.r-wrap').removeClass(_class)
        // console.log($(objHide + ' ' + '.r-wrap').attr('class').split(' '))
        $(objHide).hide();
        $(objShow).show();
        $('.p-test dd').removeClass('checked');
    }


    //文娱产品--风险评测结果展示调用(产品详情页弹层--个人)
    $('#J_p-test .submit').on('click', function () {
        scoreResult($(this), '#person-result-wy', 2);
    });
    //文娱产品--重新评测(产品详情页弹层)
    $('#person-result-wy .again').on('click', function () {
        testAgain('#person-result-wy', '#J_p-test');
    });
    //文娱产品--风险评测结果展示调用(产品详情页弹层--企业)
    $('#c-test .J_test-submit').on('click', function () {
        scoreResult($(this), '#company-result', 2);
    });
    //文娱产品--重新评测(产品详情页弹层)
    $('#company-result .again').on('click', function () {
        testAgain('#company-result', '#c-test');
    });
    //评测结果未达到级别提示信息--文娱产品详情页
    $('#person-result-wy i').on('mouseover', function () {
        resultTip('.result-tip,#person-result-wy em,#person-result-wy u');
    });
    $('#person-result-wy i').on('mouseout', function () {
        resultTipHide('.result-tip,#person-result-wy em,#person-result-wy u');
    });
    $('#company-result i').on('mouseover', function () {
        resultTip('.result-tip,#company-result em,#company-result u');
    });
    $('#company-result i').on('mouseout', function () {
        resultTipHide('.result-tip,#company-result em,#company-result u');
    });
    function resultTip(obj) {
        $(obj).fadeIn();
    }
    function resultTipHide(obj) {
        $(obj).fadeOut();
    }


    //旅游详情

    //选择时间
    $('#J_travel_con .J_date p').on('click', function () {
        $(this).addClass('active').siblings().removeClass('active');
    });
    //房产详情选择房型
    $('.travel-left .J_date p').on('click', function () {
        $(this).addClass('active').siblings().removeClass('active');
    });

    //选择等级/床型
    $('#J_travel_con .J_classify p').on('click', function () {
        $(this).addClass('active').siblings().removeClass('active');
    });
    //购买数量
    var iNum = 1;
    $('.J_plus').on('click', function () {
        iNum++
        $('.J_num').val(iNum);
    });
    $('.J_reduce').on('click', function () {
        //iNum--
        iNum == 1 ? iNum = 1 : iNum--;
        $('.J_num').val(iNum);
    });
});
