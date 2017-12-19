/**
*主页加载js
*@Joker
*/
//系统时间显示
setInterval("document.getElementById('nowTime').innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());",1000);
$(function() {
	$('#tabs').tabs({  
		border:false,
		fit:true,
		onSelect: function(title,index){
			var tab = $('#tabs').tabs('getSelected');
			var tbId = tab.attr("id");
            if(tbId==undefined){
                return;
            }
			var ids = tbId.split("_");
			var pId = ids[0];
			var cId = ids[1];
			var index = $("#menu ul#"+pId).index();
			$("#menu ul").each(function(i,o){
				if($(o).attr("id")==pId){
					$("#menu").accordion('select',i);
					return false;
				}
			});
			$(".span_click").removeClass("span_click");
			$("#"+cId).addClass("span_click");
		  }
		});
	
	
	$("#update_password").click(function(){
		$('#update_password_dialog').dialog({
		    title: "修改当前密码",
		    width: 500,
		    height: 300,
		    closed: false,
		    cache: false,
		    href: '',
		    resizable:true,
		    modal: true,
			buttons:[{
				text:'保存',
				width:"60px",
				handler:currUserPwd_update
			},{
				text:'关闭',
				width:"60px",
				handler:function(){
					$("#update_password_dialog").dialog("close");
				}
			}]
		});
	});
	
});

function reconvert(str){ 
	str = str.replace(/(\\u)(\w{1,4})/gi,function($0){ 
	return (String.fromCharCode(parseInt((escape($0).replace(/(%5Cu)(\w{1,4})/g,"$2")),16))); 
	}); 
	str = str.replace(/(&#x)(\w{1,4});/gi,function($0){ 
	return String.fromCharCode(parseInt(escape($0).replace(/(%26%23x)(\w{1,4})(%3B)/g,"$2"),16)); 
	}); 
	str = str.replace(/(&#)(\d{1,6});/gi,function($0){ 
	return String.fromCharCode(parseInt(escape($0).replace(/(%26%23)(\d{1,6})(%3B)/g,"$2"))); 
	}); 
	return str; 
	} 
/**
 * 
 * @param userid
 * @param usernm
 */
function indexInit() {
	$.ajax({
		type : "POST",
		url : "/menu/getMenus",
		contentType : "application/json; charset=utf-8",
		timeout : 20000,
		dataType : "json",
		error : function(data) {
			var msg = data.responseJSON.tips.message;
			if (msg == "该交易需要先登录") {
				window.location.href = "/pages/usermng/adminlogin";
			} else {
				$.messager.show({
					title:'警告',
					msg:msg,
					timeout:5000,
					showType:'slide'
				});
			}
		},
		success : function(data) {
			// var menus = data.res;
			getmenu(data);
		}
	});

}
/**
 * 获取菜单数据
 * @param menus
 */
function getmenu(menus) {
	// 定义存放menuid menu_code super_menu_code href i_class span_value的变量
	var menuid, menu_code, super_menu_code, href, i_class, span_value;
	$(menus).each(
			function(index) {
				var val = menus[index];
				menuid = val.menuid;
				menu_code = val.menuCode;
				super_menu_code = val.superMenuCode;
				href = val.href;
				i_class = val.iclass;
				span_value = val.spanValue;
				// menu_code能找到它的父节点非空的并且能在页面中找到父节点的处理方式
				if (super_menu_code != "") {
					var menuObj = $("#" + super_menu_code);
					if (menuObj.html() != null) {
						// alert("1 判断父节点非空，并且可以在页面中找到节点");
						var id = "menu"+menu_code;
						menuObj.append("<li style='cursor:pointer' onclick=\"addTabs('" + span_value + "','/" + href + "','" + id + "','" + super_menu_code + "')\"><div><span class='cmenu' id='"+id+"'>" + span_value + "</span></div></li> ");
					} else if (href == null || href == "#") {
						$(".easyui-accordion").accordion("add", {title : "<span>" + span_value + "</span>", content : "<ul id=\"" + menu_code + "\"></ul>",id:"menu_"+menu_code });
					} else {
						// 页面中找不到父节点，href不为空，也不为＃
						// 属于直接跳转的页面，自己不带子菜单
						// alert("3 页面中找不到父节点，href不为空，也不为＃");
						// 拼接模版文本
					}
				} else {
					// 数据中的父节点为空
					// alert("数据中的父节点为空");
				}
			});
}

/**
 * 
 * 添加一个选项卡面板 
 * @param title 菜单名字--选项卡标题
 * @param url 请求链接 
 * @param id 菜单code--选项面板id
 * @param pId 父菜单code
 */
function addTabs(title, url, id, pId){
	if(!$('#tabs').tabs('exists', title)){
		$('#tabs').tabs('add',{ 
			id:pId+"_"+id,
			title:title,
            href:url,
			/*content:'<iframe src="https://www.2345.com/" frameBorder="0" border="0" scrolling="no" style="width: 100%; height: 100%;"/>',*/
			closable:true
		});
	} else {
		$('#tabs').tabs('select', title);
		var tab = $('#tabs').tabs('getSelected');
		$('#tabs').tabs('update', {
			tab: tab,
			options: {
				title: title
			}
		});
	}
}
/**
 * 修改密码
 * @returns {Boolean}
 */
function currUserPwd_update(){
	if(!$("#update_password_form").form("validate")){
		return false;
	}
	var param = {password:$("#password").val(),news_password:$("#newpassword").val()};
	console.log(param);
	$.ajax({
		type : "POST",
		url : baseUrl+"/txCtrl?txcode=userPWDUpdate",
		data : JSON.stringify(param),
		contentType : "application/json; charset=utf-8",
		timeout : 20000,
		dataType : "json",
		error : function(msg) {
			if (msg.responseJSON != undefined) {
				$.messager.alert('更新密码','更新密码失败，该交易需要先登录。','error');
			}
		},
		success : function(result) {
			var data = result.data;
			if (data.res) {
				$('#update_password_dialog').dialog("close");
			} 
			$.messager.show({
				title:'信息',
				msg:data.msg,
				timeout:5000,
				showType:'slide'
			});
		}
	});
}

/**
 * 更换主题 
 * @param type
 */
function changeTheme(type){
    var $easyuiTheme = $('#easyuiTheme');
    var url = $easyuiTheme.attr('href');
    var href = url.substring(0, url.indexOf('easyui'))+ 'easyui/themes/' + type + '/easyui.css';
    $easyuiTheme.attr('href',href);
    /* 如果使用了iframe  则要添加下面这段代码、否则的话iframe中的内容不会出现样式的改变*/
    var $iframe = $('iframe');
    if($iframe.length > 0){
        for ( var i = 0; i < $iframe.length; i++) {
            var ifr = $iframe[i];
            $(ifr).contents().find('#easyuiTheme').attr('href', href);
        }
    }
    $.cookie('easyuithemename', type, {
        expires : 7
    });
};