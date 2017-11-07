/*! jQuery v1.11.2 | (c) 2005, 2014 jQuery Foundation, Inc. | jquery.org/license */
(function(e) {
    $.fn.rotate = function(e) {
        var t = {
            width: "100%",
            modulid: "rotate",
            autoplay: true,
            timer: 5000,
            pagenavi: true,
            feed: false,
            feedcount: 100
        };
        var e = $.extend(t, e);
        return this.each(function() {
            function o() {
                //  设置上下箭头位置
                function o() {
                    $(e.modulid + ">div").css({
                        left: ($(e.modulid).width() - $(e.modulid + ">div").width()) / 2
                    })
                }
                //  为滚动元素添加class，同时调整滚动容器左侧信息
                function u() {
                    $(e.modulid + " ul li").eq(r[0]).addClass("big");
                    $(e.modulid + " ul li").eq(r[1]).addClass("medium");
                    $(e.modulid + " ul li").eq(r[2]).addClass("small");

                    oldTime = new Date();
                    var index = $(".rotate li.big").index();
                    $(".quality .message li").eq(index).show().siblings("li").hide();
                }
                //  点击向上箭头事件
                function a() {
                    t--;
                    if (t < 0) t = n;
                    l()
                }
                //  点击向下箭头事件
                function f() {
                    t++;
                    if (t == n + 1) t = 0;
                    l()
                }
                function l() {
                    $(e.modulid + " ul li").attr("class", "");
                    if (t == 0) {
                        r[0] = t;
                        r[1] = t + 1;
                        r[2] = t + 2;
                    } else if (t == 1) {
                        r[0] = t;
                        r[1] = t + 1;
                        r[2] = 0;
                    } else if (t == 2) {
                        r[0] = t;
                        r[1] = t - n;
                        r[2] = t - 1;
                    } else if (t == n) {
                        r[0] = t;
                        r[1] = t - n;
                        r[2] = t - 1;
                    } else if (t == n - 1) {
                        r[3] = t;
                        r[4] = t + 1;
                        r[5] = 0;
                    } else if (t == n - 2) {
                        r[3] = t;
                        r[4] = t + 1;
                        r[5] = t + 2;
                    } 
                    else {
                        r[3] = t;
                        r[4] = t + 1;
                        r[5] = t + 2;
                    }

                    u();
                }
                //  滚动元素绑定点击事件
                $(e.modulid + " ul li").click(function(e) {
                    t = $(this).index();
                    l()
                });
                //  有箭头时，设置箭头样式
                if (e.pagenavi) {
                    $(e.modulid).append('<div class="arrow"></div>');
                }
                //  箭头绑定点击事件
                $(e.modulid + ">div").click(function(e) {
                    // if ($(this).attr("class") == "arrow") a();
                    // else f()
                    $(e.modulid + " ul").addClass('animate');
                    newTime = new Date();
                    if(newTime.getTime() - oldTime.getTime()>500){
                        f()
                    }
                    
                });
                //  调用设置箭头事件和元素添加class事件
                o();
                $(window).resize(function(e){
                    o()
                });
                u();
                //  自动滚动
                if (e.autoplay) {
                    $(e.modulid + " ul").addClass('animate');
                    s = setInterval(function() {
                        f();
                    }, e.timer);
                    //  鼠标移入清除定时器，移出重设定时器
                    $(e.modulid).on("mouseover",function(){
                        clearInterval(s);
                    }).on("mouseout",function(){
                        s = setInterval(function() {
                            f();
                        }, e.timer)
                    });
                }
            }
            //  获取元素id，定义变量
            e.modulid = "#" + $(this).attr("id");
            var t = 0;
            // var n = $(e.modulid + " ul li").length - 1;
            var n = 2;
            var r = [n - 2, n - 1, n];
            var i = "";
            var s = e.modulid;
            var oldTime = new Date();
            var newTime = new Date();
            if (e.feed != false) {
                $(e.modulid + " ul").html("");
                $(e.modulid + " ul").append('<li class="big"><div class="fnt-content"></div></li>');
            } else {
                o()
            }
        })
    }
})(jQuery)