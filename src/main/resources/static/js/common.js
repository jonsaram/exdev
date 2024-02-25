(function (win, $) {
    /*========= 싱글 선택 ==========*/
    $.fn.dropdown = function(){
        var $btn_click = $('.radio');
        var $open_ul = $('ul.select_lst');
        $( $btn_click ).next().addClass("viewHide");
        $( $btn_click ).on("click", function(){
            $( this ).next().removeClass("viewHide");
        });
        $($open_ul).find("input[type=radio]").on("click", function(){
            var $var = $( this ).next().text();
            var $state = $(this).parent().children().find(".status");
            var $color = $(this).parent().children().find(".status").attr('class');
            var $htmlState = '<i class="' +  $color + '" style="padding-right:5px;top:-1px"></i>';
            if($state.hasClass("status") === true) {
                $(this).parent().parent().prev().children().prepend($htmlState);
            }
            $(this).parent().parent().prev().children().find('span').text($var);
            $(this).parent().parent().prev().children().addClass( "active" );
            $(this).next().addClass("active"); $(this).parent().siblings().find("label").removeClass("active");
            $(this).parent().parent().addClass("viewHide");

        });
        $($open_ul).find("input[type=radio]").on("focus", function(){
            var $var = $( this ).next().text();
            var $state = $(this).parent().children().find(".status");
            var $color = $(this).parent().children().find(".status").attr('class');
            var $htmlState = '<i class="' +  $color + '" style="padding-right:5px;top:-1px"></i>';
            if($state.hasClass("status") === true) {
                $(this).parent().parent().prev().children().prepend($htmlState);
            }
            $( this ).parent().parent().prev().children().text($var);
            $( this ).parent().parent().prev().children().addClass( "active" );
            $(this).next().addClass("active"); $(this).parent().siblings().find("label").removeClass("active");
        });
        $($open_ul).find("input[type=radio]").on("blur", function(){
            $( this ).parent().parent().prev().children().removeClass( "active" );
        });
        $( $btn_click ).next().on("mouseleave", function(){
            $( $btn_click ).next().addClass( "viewHide" );
        });
    };


    /*============= 멀티선택 ================*/
    $(document).on("click", ".dropdown button", function(){
        $(this).parent().children().find("ul").slideToggle('fast');
    });

    // 외부 링크
    $(document).bind('click', function(e) {
      var $clicked = $(e.target);
      if (!$clicked.parents().hasClass("dropdown")) {
        $(".dropdown ul").hide();
      };
    });

    //체크박스 체크할때
    $(document).on("click", ".mutliSelect input[type='checkbox']", function(){
      var title = $(this).closest('.mutliSelect').find('input[type="checkbox"]').val(),
        title = $(this).val() + ",";

      if ($(this).is(':checked')) {
        var html = '<span title="' + title + '">' + title + '</span>';
        $(this).closest('.mutliSelect').parent().children().find(".multiSel").append(html);
        $(this).closest('.mutliSelect').parent().children().find(".hida").hide();
      }
      else {
        $(this).closest('.mutliSelect').parent().children().find(".multiSel").find('span[title="' + title + '"]').remove();
        var checkLength = $(this).closest('.mutliSelect').parent().children().find(".multiSel").find('span').length;
        if(checkLength == 0){
            $(this).closest('.mutliSelect').parent().children().find(".hida").show();
        }
      }
    });


    /*==== Gnb 영역 너무 길때 생긴 버튼 ====*/
    // $(document).on("click", ".btn_arrow", function(){
    //     $(this).parents().find('.wrap').toggleClass('resize');
    //     $(this).toggleClass('on');
    // });

    /*==== Gnb menu active ====*/
    $(document).on("click", ".gnb li a", function(){
        $('.gnb li').removeClass('active');
        $(this).parent().addClass('active');
    });

    /*==== User menu ====*/
    $(document).on("click", ".user_login", function(){
        $(this).toggleClass('rotate');
        $(this).next().toggleClass('block');
    });
    $(document).on("click", ".user_menu li a", function(){
        $(this).parent().parent().removeClass('block');
        $(this).parent().parent().prev().removeClass('rotate');
    });
    $('html').click(function(e){
        var $clicked2 = $(e.target);
        if (!$clicked2.hasClass("user_menu")) {
            $('.user_menu').removeClass('block');
            $('.user_login').removeClass('rotate');
        };
    });

    /*==== Lnb menu active ====*/
    $(document).on("click", ".lnb li li>a", function(){
        $('.lnb li li').removeClass('active');
        $(this).parent().addClass('active');
    });
    /*====== 달력 =======*/
    $(function () {
        $(".datepicker").datepicker({
          showOn: "button",
          buttonImage: "../img/icon_calendar.png",
          buttonImageOnly: true,
          buttonText: "Select date",
        });
    });
    /*====== 달력 =======*/
    $( function() {
        var dateFormat = "mm/dd/yy",
            from = $( "#from" )
            .datepicker({
                defaultDate: "+1w",
                changeMonth: true,
                numberOfMonths: 1
            })
            .on( "change", function() {
                to.datepicker( "option", "minDate", getDate( this ) );
            }),
            to = $( "#to" ).datepicker({
                defaultDate: "+1w",
                changeMonth: true,
                numberOfMonths: 1
            })
            .on( "change", function() {
                from.datepicker( "option", "maxDate", getDate( this ) );
            });

        function getDate( element ) {
            var date;
            try {
                date = $.datepicker.parseDate( dateFormat, element.value );
                } catch( error ) {
                date = null;
            }
            return date;
        }
    });

    /*====== 레이어 파업 열고 닫기 =======*/
    $(function ($) {
        var $modal = $('.modal_wrap');

        // Modal Anchor Click
        $('a[href^="#modal-"]').click(function(){
            var $this = $(this);
            var $target = $($this.attr('href'));
            $this.addClass('active');
            $target.appendTo('body').attr('tabindex','0').fadeIn(200).focus(); // Target is move to body, and show.
            return false;
        });

        // Close modal window and bg
        function closeModal(){
            $('a[href^="#modal-"].active').focus().removeClass('active');
        };
        // Close button
        $('.btn_close,.modal_footer button').click(function(){
            $(this).parent().parent().parent().fadeOut(200);
        });
        $modal.find('.btn_close').click(function(){
            closeModal();
        });
        // ESC button
        $(document).keydown(function(e){
            if(e.keyCode != 27) return true;
            return closeModal();
        });
    });

    /*====== 테이블 헤더 고정 =======*/
    $(function(){
        $.fn.hasYScrollBar = function() {
            return (this.prop("scrollHeight") == 0 && this.prop("clientHeight") == 0) || (this.prop("scrollHeight") > this.prop("clientHeight"));
        };

        $.fn.hasXScrollBar = function() {
            return (this.prop("scrollWidth") == 0 && this.prop("clientWidth") == 0) || (this.prop("scrollWidth") > this.prop("clientWidth"));
        };

        $(".tbl_body_scroll").scroll(function(event){
            var sl = 0;
            // data 테이블 x축 스크롤을 움직일 때header 테이블 x축 스크롤을 똑같이 움직인다
            if (sl != $(".tbl_body_scroll").scrollLeft()) {
                sl = $(".tbl_body_scroll").scrollLeft();
                $(".tbl_head").scrollLeft(sl);
            }
        });

        if ($(".tbl_body_scroll").hasYScrollBar()) {
            //y축 스크롤이 있으면 스크롤 넓이인 8px만큼 header 마지막 열 크기를 늘린다
            $(".tbl_head colgroup col:last-child").width($(".tbl_body_scroll colgroup col:last-child").width() + 8 );
        }else{
            $(".tbl_head colgroup col:last-child").width();
        }
    });

    /*==== lnb 영역 접고/닫기 버튼 ====*/
    $(document).on("click", ".btn.fold", function(){
        $(this).parent().parent().parent().toggleClass('collapse');
    });

    /*==== 결재 버튼 ====*/
    $(document).on("click", ".toggle", function(){
        $(this).parent().children().removeClass('active');
        $(this).addClass('active');
    });
    $(document).on("click", ".pay_close", function(){
        $(this).parent().remove()
    });
    $(document).on("click", ".approver_list_wrap.person .approver_list li", function(){
        $('.approver_list_wrap.person .approver_list li').removeClass('active');
        $(this).addClass('active');
    });

    /*==== section 영역 스크롤 있을때 영역 ====*/
    $(function(){
        $.fn.hasScrollBar = function() {
            return (this.prop("scrollHeight") == 0 && this.prop("clientHeight") == 0) || (this.prop("scrollHeight") > this.prop("clientHeight"));
        };

        // 스크롤 존재할 경우
        if( $(".sec_scroll").hasScrollBar() ){
            $(".sec_scroll").addClass('resize');
        }else{
           $(".sec_scroll").removeClass('resize');
        }
    });

    /*==== 첨부파일 추가 ====*/
    $(document).on("click", ".file", function(){
        $('input[type=file]#file-input').click();
    });
    // $(document).on("change", "input[type=file]#file-input", function(){
    //     var title = $(this).val();
    //     var html = '<li><span class="txt">'+ title +'</span><button class="pay_close">닫기</button></li>';
    //     $(this).parent().children().find('.approver_list').append(html);
    // });

    /*==== bar chart ====*/
    $(function() {
        function animated_contents() {
            $(".zt-skill-bar .data ").each(function (i) {
                var $this  = $(this),
                    skills = $this.data('width');
                $this.css({'width' : skills + '%'});
            });
        }

        if(jQuery().appear) {
            $('.zt-skill-bar').appear().on('appear', function() {
                animated_contents();
            });
        } else {
            animated_contents();
        }
    });


    /*==== tab ====*/
    $(function() {
        //$(".display").css("display","none");
        $('div.tab_content').each(function() {
            $(this).find('div.display:first').show();
        });

        $('.tab_wrap li').click(function() {
            if (!$(this).hasClass('active')) {
                $(this).addClass('active').siblings('li').removeClass('active');
                $($(this).children().attr('href')).show("100").siblings('div.display').hide("100");
            }
        });
    });


    /*============= Search Area ================*/
    $(document).on("click", "#searchBtn", function(){
        $(this).next().show();
    });
    $(document).on("click", ".search_lst", function(){
        $(this).hide();
    });


    /*============= table select ================*/
    $(document).on("click", ".tbl01 tr", function(){
        $(this).addClass('select').siblings('tr').removeClass('select');
    });
    // 외부 링크
    $(document).bind('click', function(e) {
      var $clicked = $(e.target);
      if (!$clicked.parents().hasClass("tbl01")) {
        $(".tbl01 tr").removeClass('select');
      };
    });


    /*==== consulting active ====*/
    $(document).on("click", ".listBtn li a", function(){
        $('.listBtn li a').removeClass('active');
        $(this).addClass('active');
    });

})(window, window.jQuery);

$(document).ready(function() {
    $('.select_box').dropdown();
});