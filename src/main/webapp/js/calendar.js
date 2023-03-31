var dateobj = prev();

function prev(){
    var _date = new Date();    // 默认为当前系统时间
    return {
        getDate : function(){
            return _date;
        },
        setDate : function(date) {
            _date = date;
        }
    };
}

$(function(){

    renderhtml();    //对日历div中增加表格元素
    showCalendarData();  //在表格中显示日期
    bindEvent();   //对表格上方的上月下月绑定事件
})


function renderhtml(){
    var calendar=document.getElementById("calendar");
    var titlebox=document.createElement("div");  //在div中设置标题区  显示当前月份和上月下月的控制
    var bodybox=document.createElement("div");   //主体部分 用于存放日历表格

    titlebox.className="calendar-title-box";   //给标题box绑定属性   并向div中添加元素
    titlebox.innerHTML="<span id='prevMonth' class='prev-month'></span>"+
        "<span class='calendar-title' id='calendarTitle'></span>"+
        "<span class='next-month' id='nextMonth'></span>";

    calendar.appendChild(titlebox);  //将标题box设为日历容器的子节点
    bodybox.className='calendar-body-box';   //表格部分绑定属性
    var headhtml="<tr>"+"<th>日</th>"+"<th>一</th>"+
        "<th>二</th>"+"<th>三</th>"+"<th>四</th>"+
        "<th>五</th>"+"<th>六</th>"+"</tr>"; // tr是行，th是表头，td是列
    var bodyhtml="";

    for(var i=0;i<6;i++){      //一周7天 字符串拼接放到bodyhtml中
        bodyhtml+="<tr>"+"<td onclick='editSchedules(this)' onMouseOver=\"this.style.background='#4dd5d5'\" onMouseOut=\"this.style.background='#fff'\"></td>"+
            "<td onclick='editSchedules(this)' onMouseOver=\"this.style.background='#4dd5d5'\" onMouseOut=\"this.style.background='#fff'\"></td>"+
            "<td onclick='editSchedules(this)' onMouseOver=\"this.style.background='#4dd5d5'\" onMouseOut=\"this.style.background='#fff'\"></td>"+
            "<td onclick='editSchedules(this)' onMouseOver=\"this.style.background='#4dd5d5'\" onMouseOut=\"this.style.background='#fff'\"></td>"+
            "<td onclick='editSchedules(this)' onMouseOver=\"this.style.background='#4dd5d5'\" onMouseOut=\"this.style.background='#fff'\"></td>"+
            "<td onclick='editSchedules(this)' onMouseOver=\"this.style.background='#4dd5d5'\" onMouseOut=\"this.style.background='#fff'\"></td>"+
            "<td onclick='editSchedules(this)' onMouseOver=\"this.style.background='#4dd5d5'\" onMouseOut=\"this.style.background='#fff'\"></td>"+"</tr>";
    }
    bodybox.innerHTML="<table id='calendarTable' class='table table-bordered text-center'>"+
        headhtml+bodyhtml+"</table>";  //设置表格box的内容

    calendar.appendChild(bodybox);

}



function showCalendarData(){
    //alert(dateobj);
    var year=dateobj.getDate().getFullYear();   //dateobj为object对象 通过getDate得到日期 再得到年
    var month=dateobj.getDate().getMonth()+1;   // 起始值是0
    var datestr=getdatestr(dateobj.getDate());  //通过函数gatdatestr将dateobj转换成字符串格式

    //设置表格顶部的年月信息
    var calendarTitle=document.getElementById("calendarTitle");
    var titleStr=datestr.substr(0,4)+"年"+datestr.substr(4,2)+"月";  //通过substr截取字符串  得到年的数字和月份数字
    calendarTitle.innerText=titleStr;   //将拼接后的****年**月显示在上方

    //设置表格中的日期数据
    var table=document.getElementById("calendarTable");
    var tds=table.getElementsByTagName("td");
    var firstday=new Date(year,month-1,1);  //当前月的第一天
    //alert(firstday.getDay());

    for(var i=0;i<tds.length;i++){
        var thisday=new Date(year,month-1,i+1-firstday.getDay());  //getDay得到第一天是周几 从而显示第一行上个月的数据
        var thisdayStr=getdatestr(thisday);

        tds[i].innerHTML="<span style='font-size:16px; font-wight:700'>"+thisday.getDate()+"</span>"+   // getDate返回1-31之间的数
            "<br/><div></div>";

        tds[i].setAttribute("data",thisdayStr);
        if(thisdayStr==getdatestr(new Date())){
            tds[i].className="currentDay";   //当天设置属性
        }else if(thisdayStr.substr(0,6)==getdatestr(firstday).substr(0,6)){
            tds[i].className="currentMonth";  //当前月设置属性
        }else{
            tds[i].className="otherMonth";      //其他月的日历设置属性
        }
    }

        // 日程列表添加
    $("#calendarTable tbody tr").each(function (){
        $(this).children('td').each(function(){

            var date = $(this).attr("data");

            $(this).children('div').each(function(){

                $.post("../updateCalendar","date="+date, function(data) {
                    //console.log(data)
                    $(this).html(data);

                }.bind(this))   // bind是绑定，因为ajax是异步操作
            })
        })
    })

}


function bindEvent(){
    var prevMonth=document.getElementById("prevMonth");
    var nextMonth=document.getElementById("nextMonth");
    addEvent(prevMonth,'click',toPrevMonth);    //绑定点击事件
    addEvent(nextMonth,'click',toNextMonth);
    //addEvent($(":radio"),'click',toChooseSch);
}

function getdatestr(date){
    var year=date.getFullYear();
    var month=date.getMonth()+1;  //月份从0开始
    var day=date.getDate();
    month=(month>9)?(""+month):("0"+month);  //如果得到的数字小于9要在前面加'0'
    day=(day>9)?(""+day):("0"+day);

    return year+month+day;
}

function addEvent(dom,eType,func){
    if(dom.addEventListener){
        dom.addEventListener(eType,function(e){
            func(e);
        });
    }else if(dom.attachEvent){
        dom.attachEvent('on'+eType,function(e){
            func(e);
        });
    }else{
        dom['on'+eType]=function(e){
            func(e);
        }
    }
}

function toPrevMonth(){
    var date=dateobj.getDate();
    dateobj.setDate(new Date(date.getFullYear(),date.getMonth()-1,1));
    showCalendarData();
}

function toNextMonth(){
    var date=dateobj.getDate();
    dateobj.setDate(new Date(date.getFullYear(),date.getMonth()+1),1)
    showCalendarData();
}

function editSchedules(element){
    var data = element.getAttribute("data")

    //console.log(data)

    // 使用encodeURIComponent()方法对参数值进行编码
    var encodedDatas = encodeURIComponent(data);

    // 将编码后的参数添加到 URL 查询字符串中
    // var newUrl = 'operationSchedules.jsp?date=' + encodedDatas;
    var newUrl = "../schedules?date=" + encodedDatas

    // 使用window.location.href属性设置新的URL
    // window.location.href = newUrl
    window.parent.location.href=newUrl
    // top.location.href = newUrl

}