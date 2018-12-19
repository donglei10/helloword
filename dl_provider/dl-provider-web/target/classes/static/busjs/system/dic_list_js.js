$(function () {

    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();
    
    
    $('.dic_opt_add').bind("click", function(){
	    art.dialog.open('/dic/edit', {
	        width: 1000,
	        height: 420,
	        title: '添加'
	    }, false);
   	
    });
    
    $('.dic_opt_update').bind("click", function(){
    	art.dialog.open('/dic/edit', {
	        width: 1000,
	        height: 420,
	        title: '编辑'
	    }, false);
    });
    
    $('.dic_opt_del').bind("click", function(){
    	//批量删除
    });
    
});

$.fn.serializeJsonObject = function () {
    var json = {};
    var form = this.serializeArray();
    $.each(form, function () {
        if (json[this.name]) {
            if (!json[this.name].push) {
                json[this.name] = [json[this.name]];
            }
            json[this.name].push();
        } else {
            json[this.name] = this.value || '';
        }
    });
    return json;
}


var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_dics').bootstrapTable({
            url: '/dic/listData',         //请求后台的URL（*）
            method: 'post',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [{
                checkbox: true
            }, {
                field: 'tCode',
                title: '编码值'
            }, {
                field: 'tText',
                title: '编码内容'
            }, {
                field: 'tType',
                title: '编码级别'
            }, {
                field: 'tRemark',
                title: '描述'
            }, {
                field:'id',
                title: '操作',
                width: 120,
                align: 'center',
                valign: 'middle',
                formatter: actionFormatter
            }]
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        /*var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit,   //页面大小
            offset: params.offset,  //页码
            tCode: $("#txt_search_tCode").val(),
            tText: $("#txt_search_tText").val()
        };*/
        var temp = $("#formSearch").serializeJsonObject();
        
        return temp;
    };
    return oTableInit;
};

//操作栏的格式化
function actionFormatter(value, row, index) {
    var id = value;
    var result = "";
    result += "<a href='javascript:;' class='btn btn-xs green' onclick=\"viewById('" + id + "', view='view')\" title='查看'><span class='glyphicon glyphicon-search'></span></a>";
    result += "<a href='javascript:;' class='btn btn-xs blue' onclick=\"editById('" + id + "')\" title='编辑'><span class='glyphicon glyphicon-pencil'></span></a>";
    result += "<a href='javascript:;' class='btn btn-xs red' onclick=\"deleteById('" + id + "')\" title='删除'><span class='glyphicon glyphicon-remove'></span></a>";

    return result;
}

function viewById(id){
	alert("查看");
}

function editById(id){
	alert("编辑");
}

/**
 * 批量删除
 * @param id
 * @returns
 */
function deleteByIds(id){
	//获取所有被选中的记录 
	  var rows = $("#tb_dics").bootstrapTable('getSelections'); 
	  if (rows.length== 0) { 
	    alert("请先选择要删除的记录!"); 
	    return; 
	  } 
	  var ids = ''; 
	  for (var i = 0; i < rows.length; i++) { 
	    ids += rows[i]['id'] + ","; 
	  }
	  ids = ids.substring(0, ids.length - 1); 
	  eventEleteByIds(ids); 
}

/**
 * 单个删除
 * @param id
 * @returns
 */
function deleteById(id){
	eventEleteByIds(id);
}

/**
 * 删除后台数据
 * @param ids
 * @returns
 */ 
function eventEleteByIds(ids) { 
  var msg = "您真的确定要删除吗？"; 
  if (confirm(msg) == true) { 
    $.ajax({ 
      url: "/dic/deleteByIds", 
      type: "post", 
      data: { 
        ids: ids 
      }, 
      success: function (data) { 
    	  dicSearch(text);
      } 
    }); 
  } 
}

function dicSearch(text) {
	$("#tb_dics").bootstrapTable('refresh');//刷新Table，Bootstrap Table 会自动执行重新查询
}
 