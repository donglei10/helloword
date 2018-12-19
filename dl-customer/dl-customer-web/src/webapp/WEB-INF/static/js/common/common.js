String.prototype.replaceAll = function(s1,s2){ 
	return this.replace(new RegExp(s1,"gm"),s2); 
};

function createTooltip(){  
	$(".datagrid-htable tr").each(function(){
		$("td").each(function(){
			$(this).attr("title",$(this).text());
		});
	}); 
	
}

/**
 * 下载文件列表
* bzid 业务主键
* model 文件类型
* id  div的id
*/
function fileList(model,bzid,id,type,image,async,other){
	if(!bzid || bzid==null || bzid ==""){
		return;
	}
	$("#"+id).html("");
	var url = path+ "/fileUpDownController/model/"+bzid+"/"+model;
	var obj   = $("<ul style='list-style:demical;margin:0px;text-align: left;padding-left:1;'></ul>");
	var imageurl = "";
	var bigurl = "";
	if(!async){
		async = false;
	}
	$.ajax({
		  type: 'POST',
		  url: url,
		  data: {},
		  dataType: "json",
		  async:async,
		  success:  function(data){
			    if(data && data.length==1){
					//obj.css("list-style","none");
				}
				for(var i in data){
					imageurl = path+"/fileUpDownController/download/"+data[0].id+"?bzid="+bzid;
					bigurl = data[i].id+"?bzid="+bzid;
					var str = "";
					if(image && image=="image"){
						str = "<li id='"+data[i].id+"' style='text-align: left;'> <a  style='cursor: pointer;color: blue;' onclick='show_Image(\""+bigurl+"\")' target= _blank >"+data[i].fileName+"</a>&nbsp;&nbsp;" 
					}else{
						str = "<li id='"+data[i].id+"' style='text-align: left;'> <a  style='cursor: pointer;color: blue;'  href='"+imageurl+"' target= _blank >"+data[i].fileName+"</a>&nbsp;&nbsp;" 
					}
					if(type && type=="input"){
							str	+=	" <a href='#' onclick=delFile('"+bigurl+"','"+bzid+"','"+data[i].id+"','"+id+"') >删除</a>" 
						}
						str	+=	"</li>";
					if(type && type=="share")
					{
						//not need in share page
					}else{
						obj.append(str);
					}				
				}
			}
		}); 
	if(image && image=="image"){
		if(other && other.image){
			obj.append(	' </br><img alt="" style="width: '+other.image.width+'px;height: '+other.image.height+'px;" onclick="show_Image(\''+bigurl+'\')" src="'+imageurl+'"> ');
		}else{
			obj.append(	' </br><img alt="" onclick="show_Image(\''+bigurl+'\')" src="'+imageurl+'"> ');
		}
	}
	$("#"+id).html(obj);
}
/**
 * 展示大图片
 * v = imageid+"?bzid="+bzid
 * @param v 
 */
function show_Image(v){
	var url =  path+"/fileUpDownController/showBigImage?pathImage="+v;
	art.dialog.open(url, {
			width : 990,
			height : 500,
			title : '查看图片'					 
		}, false);
}
/**
 * 删除文件
* bzid 业务主键
* model 文件类型
* id  div的id
*/
function delFile(model,bzid,dataId,id){
	var url = path+"/fileUpDownController/delete/"+dataId+"?bzid="+bzid;
	$.post(url,null,function(data){ 
		if(data && data=="success"){
			$("#"+dataId).remove();
		}
	});
}

/**
* dicType 字典类型
* view </br>
	1.select 时生效： query 不限, select 请选择</br>
	2.</br>
* type 展示类型  select  radio  checkbox</br>
* val 选中的值</br>
* id 替换标签div的id</br>
*/
function dicList(dicType,view,type,val,id){
	var url = path + "/ajax/ajaxBaseDicController/listDic";
	var style = $("#"+id).attr("style");
	var cls =  $("#"+id).attr("class");
	if(!cls){
		cls = "";
	}
	var name =  $("#"+id).attr("name");
	var obj ;
	if(type=="select"){
		obj = $("<select id='"+id+"' name='"+name+"' style='"+style+"' class=' "+cls+"' ></select>")
		if(view=="query"){
			obj.append("<option value=''>不限</option>");
		}else if(view=="select"){
			obj.append("<option value=''>请选择</option>");
		}
		$.post(url,{dicType:dicType},function(data){
			for(var i in data){
				var str = "<option value='"+data[i].dicCode+"'>"+data[i].dicValue+"</option>";
				if(data[i].dicCode==val){
					str = "<option value='"+data[i].dicCode+"'  selected='selected' >"+data[i].dicValue+"</option>";
				} 
				obj.append(str);
			}
		});
	}else if(type=="radio"){
		
	}else if(type=="checkbox"){
		
	}else if(type=="view"){
		$("#"+id).replaceWith(showDic(dicType,val));
	}
	$("#"+id).replaceWith(obj);
}

  function showDic(dicType,val,async){
	var url = path + "/ajax/ajaxBaseDicController/listDic";
	var dictext;
	if(!async){
		async = false;
	}
	$.ajax({
		  type: 'POST',
		  url: url,
		  data: {dicType:dicType},
		  dataType: "json",
		  async:async,
		  success:  function(data){
				for(var i in data){
					if(data[i].dicCode==val){
						dictext = data[i].dicValue;
					} 
				}
			}
		}); 
	return dictext;
}


