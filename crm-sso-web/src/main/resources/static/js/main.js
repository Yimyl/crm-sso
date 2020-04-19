// JavaScript Document

//全局加载事件
$(function () {
    globalEvent.gridRows();
    globalEvent.dateTimePicker();
    globalEvent.setWordTips();
    globalEvent.simList();
    globalEvent.commonTabs();
});

/*===============================================*/
/*加事件*/
var globalEvent = {
    /*自适应按钮效果*/
    btnOver: function () {
        $(".btn").live('mouseover',
            function () {
                $(this).addClass("btnOver");
            }).live('mouseout',
            function () {
                $(this).removeClass("btnOver");
            });
    },
    /*inupt输入框换背景*/
    eachEditor: function () {
        $("input:text,input:password,textarea").focus(function () {
            $(this).addClass("editBgFocus");
        }).blur(function () {
            $(this).removeClass("editBgFocus");
        });
    },
    /*日期时间选择器*/
    dateTimePicker: function () {
        if ($(".dateSet").length > 0 || $(".timeSet").length > 0 || $(".YMSet").length > 0 || $(".dateTimeSet").length > 0) {
            /*日期*/
            $(".dateSet").dynDateTime();
            /*时间*/
            $(".timeSet").dynDateTime({
                showsTime: true,
                ifFormat: "%H:%M"
            });
            /*年月*/
            $(".YMSet").dynDateTime({
                ifFormat: "%Y-%m"
            });
            /*日期+时间*/
            $(".dateTimeSet").dynDateTime({
                showsTime: true,
                ifFormat: "%Y-%m-%d %H:%M:00"
            });
        }
    },
    gridRows: function () {
        if ($('.gridRows').length > 0) {
            $('.gridRows tbody tr').not('tr.thead')
                .mouseenter(function () {
                    $(this).addClass('trHover');
                    //console.log($(this).parent().find('tr').index(this));
                    var n = $(this).parent().find('tr').index(this);
                    $('.fixedColsL,.fixedColsR,.scrollCon', $(this).parents('.fixedTable')).each(function () {
                        $($('.grid tbody tr', this)[n]).addClass('trHover');
                    })
                })
                .mouseleave(function () {
                    $(this).removeClass('trHover');
                    var n = $(this).parent().find('tr').index(this);
                    $('.fixedColsL,.fixedColsR,.scrollCon', $(this).parents('.fixedTable')).each(function () {
                        $($('.grid tbody tr', this)[n]).removeClass('trHover');
                    })
                });
        }
    },
    /*滚动至顶部*/
    scrollToTop: function (right, bottom) {
        if ($("#scrollToTop").length > 0) {
            $("#scrollToTop").css({'right': right, 'bottom': bottom});
            $("#scrollToTop").hover(function () {
                $(this).stop(true).animate({'opacity': '1'}, 200);
            }, function () {
                $(this).stop(true).animate({'opacity': '0.6'}, 200);
            });
            $("#scrollToTop").click(function () {
                $("html, body").animate({'scrollTop': '0'}, 200);
                return false;
            });
            $(window).bind('scroll', function () {
                var st = $(document).scrollTop();
                (st > 0) ? $("#scrollToTop").show() : $("#scrollToTop").hide();
            });
        }
    },
    //表格选择变色
    gridSelect: function () {
        if ($(".gridSelect").length > 0) {
            $(".gridSelect tr").click(function () {
                $(this).addClass("selected").siblings().removeClass("selected");
            });
        }
    },
    setAlertLayer: function (type, str, isFadeOut, time, func) {
        if ($('.alert-layer').length > 0) {
            $('.alert-layer').remove();
        }
        if (type != 'success' && type != 'alert' && type != 'failure') {
            type = 'alert';
        }
        if (isFadeOut == undefined) {
            isFadeOut == false;
        }
        if (func == undefined) {
            func = function () {
            };
        }
        if (time == undefined) {
            time = 5000;
        }
        var temp = $('<div class="alert-layer"><div class="alert-layer-top"><span class="close">关闭</span></div><div class="alert-layer-content clearfix"><span class="' + type + '"></span><span class="info">' + str + '</span></div></div>');
        temp.find('.close').bind('click', function () {
            $(this).parents('.alert-layer').animate({"opacity": 0}, 200, function () {
                $(this).remove();
                func();
            });
        });
        $('body').append(temp);
        if (isFadeOut) {
            setTimeout(function () {
                temp.animate({"opacity": 0}, 200, function () {
                    temp.remove();
                    func();
                })
            }, time);
        }
    },
    setWordTips: function () {
        if ($('.word-tips').length > 0) {
            $('.word-tips').bind('mouseenter', function () {
                var l = $(this).position().left;
                var t = $(this).position().top + $(this).height();
                var str = $(this).attr('word-tips');
                if (str.length) {
                    var temp = $('<span class="tips-box" style="left:' + l + 'px; top:' + t + 'px;">' + str + '</span>');
                    $(this).append(temp);
                    $(this).bind('mouseleave', function () {
                        temp.stop(true).animate({"opacity": 0}, 200, function () {
                            temp.remove();
                        })
                    })
                }
            });
        }
    },
    simList: function () {
        if ($('.sim-list').length > 0) {
            $('.sim-list').live('mouseenter', function () {
                $(this).addClass('hover');
            })
                .live('mouseleave', function () {
                    $(this).removeClass('hover');
                });
        }
    },
    /* 通用tabs */
    commonTabs: function () {
        if ($('.tabsEvent').length > 0) {
            $('.tabsEvent').find('span.title-item').live('click', function () {
                var targetId = $(this).attr('target-id');
                $(this).addClass('cur').siblings().removeClass('cur');
                $(this).parents('.tabsEvent').find('#' + targetId).removeClass('hidden').siblings().addClass('hidden');
            });
        }
    }
};

var loadingMask = {
    //设置屏蔽层
    setLoading: function (a) {
        this.removeLoading(a);
        var h = $(a).outerHeight();
        var w = $(a).outerWidth();
        var l = $(a).position().left;
        var t = $(a).position().top;
        var str = $('<div class="data-loading"><div class="data-loading-mask"></div><div class="data-loading-ico"></div></div>');
        str.css({
            'height': h,
            'width': w,
            'left': l,
            'top': t
        });
        $(a).after(str);
    },
    //移除屏蔽层
    removeLoading: function (a) {
        if ($(a).next('.data-loading').length != 0) {
            $(a).next('.data-loading').remove();
        }
    },
    setError: function (a, str) {
        this.removeLoading(a);
        var h = $(a).outerHeight();
        var w = $(a).outerWidth();
        var l = $(a).position().left;
        var t = $(a).position().top;
        var str = $('<div class="data-loading"><div class="data-loading-mask"></div><div class="data-error">' + str + '</div></div>');
        str.css({
            'height': h,
            'width': w,
            'left': l,
            'top': t
        });
        $(a).after(str);
    }
};