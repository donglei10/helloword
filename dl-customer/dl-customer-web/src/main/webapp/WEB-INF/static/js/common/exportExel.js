		var $win;
		var $excelArrays;
		var $first=-1;
		var $last=-1;
		
		/**
		 * 初始化导出
		 */
		$(function (){
			var div = $('<div id="exportExcelDiv" class="easyui-window"  style="width:560px;height:140px;padding:10px;"></div>');
			var form = $('<form id="exportSubmit" name="exportSubmit" method="post" action="'+path+'/sys/exportExcelController/exportExcel"></form>');
			var hidediv ='<div id="hidediv"></div> ';
			form.append(hidediv);
			var hidedivParams ='<div id="hidedivParams" style="display:none;" ></div> ';
			form.append(hidedivParams);
			var tablestr = '';
			tablestr+='<table id="ddd" align="center" class="my_table" width="100%" height="100%">';
			tablestr+='<td align="center" style="background-color: #ccc" width="10%"><input type="checkbox" onclick="clickCheckAll()" id="checkAll" checked="checked" name="checkAll" value="" ></td>';
			tablestr+='<td align="center" style="background-color: #ccc" width="50%">导出列</td>';
			tablestr+='<td align="center" style="background-color: #ccc" width="10%">上一行</td>';
			tablestr+='<td align="center" style="background-color: #ccc" width="10%">顶行</td>';
			tablestr+='<td align="center" style="background-color: #ccc" width="10%">下一行</td>';
			tablestr+='<td align="center" style="background-color: #ccc" width="10%">底行</td>';
			tablestr+='</tr>';
			tablestr+='<tbody id="ecbody"></tbody>';
			tablestr+='<tr>';
			tablestr+='<td colspan="6" align="center">';
			tablestr+='<input id="exportButton" type="button" onclick="exportSubmitExcel()" value="导出本页" /> ';
			tablestr+='<input id="exportButtonAll" type="button" onclick="exportSubmitExcelAll()" value="导出所有" /> ';
			tablestr+='<input id="closeButton" type="button" onclick="$(\'#exportExcelDiv\').window(\'close\');" value="关闭" />';
			tablestr+='</td>';
			tablestr+='</tr>';
			tablestr+='<tr >';
			tablestr+='</table>';
			var table =$(tablestr);
			form.append(table);
			
			
			div.append(form);	
			$win = div.window({
			    title: '选择按照查询条件导出Excel的列',
			    width: 560,
			    height: 400,
			    top: 50,
			    shadow: true,
			    modal: true,
			    iconCls: 'icon-redo',
			    closed: true,
			    minimizable: false,
			    maximizable: false,
			    collapsible: false
			});
		});
		
		/**
		* Excel 导出
		*/
		function exportExcelByGride(param){
			$("#ecbody").html('');
			hideDiv(param);
			var opts = $('#'+param.gridid).datagrid('getColumnFields');
			var tempnum = 0; 
			for(var i in opts){
				//排除grid中不需要导出的列
				for(var j in param.noArray){
					if(i==param.noArray[j]){
						tempnum =1;
						break;
					}
				}
				if(tempnum==1){
					tempnum = 0;
					continue;
				}
				
				if($first <0){//初始化第一个行号
					$first = i;
				}
				$last = i; //初始化最后一个行号
				var o = $('#'+param.gridid).datagrid('getColumnOption',opts[i]);
				$("#ecbody").append(getBodeyStrByKeyValueIndex(opts[i],o.title,i));
				
			}
			$win.window('open');
		}
		
		/**
		* Excel 导出
		*/
		function exportExcelByArray(param){
			$("#ecbody").html('');
			hideDiv(param);
			$excelArrays = param.excelArray;
			for(var i in param.excelArray){
				$("#ecbody").append(getBodeyStrByKeyValueIndex(param.excelArray[i].key,param.excelArray[i].vals,i));
			}
			$win.window('open');
		}
		
		/**
		 * 拼接隐藏域
		 * @param param 参数
		 */
		function hideDiv(param){
			var hidestr = ' <input id="title"  type="hidden"  name="title" value=\''+param.title+'\' /> ';
			hidestr += ' <input id="service"  type="hidden"  name="service" value=\''+param.service+'\' /> ';
			hidestr += ' <input id="method" type="hidden"  name="method" value=\''+param.method+'\' /> ';
			hidestr += ' <input id="vok" type="hidden"  name="vok" value=\''+param.vok+'\' /> ';
			hidestr += ' <input id="pageSize" type="hidden"  name="pageSize" value=\''+$(".pagination-page-list").val()+'\' /> ';
			hidestr += ' <input id="pageNumber" type="hidden"  name="pageNumber" value=\''+$(".pagination-num").val()+'\' /> ';
			hidestr += ' <input id="params" type="hidden"  name="params" value=\''+JSON.stringify(param.queryParams)+'\' /> ';
			if($("#hidediv").html()!=hidestr){
				$("#hidediv").html('');
				$("#hidediv").append(hidestr);
			}
		}
		
		
		/**
		 * 新增一行
		 * @param key 后台导出处理的key
		 * @param vals 后台导出处理展示的title
		 * @param index 索引
		 */
		function getBodeyStrByKeyValueIndex(key,vals,index){
			var bodystr = '';
			bodystr +='<tr id="excel_d'+index+'" >';
			bodystr +='<td align="center" width="20px;"><input type="checkbox" checked="checked"  name="check" value="'+key+'__'+vals+'" ></td>';
			bodystr +='<td align="center">'+vals+'</td>';
			bodystr +='<td align="center"><a href="javaScript:upDwon('+index+',\'up\')">↑</a></td>';
			bodystr +='<td align="center"><a href="javaScript:upAllDwon('+index+',\'up\')">↑↑</a></td>';
			bodystr +='<td align="center"><a  href="javaScript:upDwon('+index+',\'down\')">↓</a></td>';
			bodystr +='<td align="center"><a  href="javaScript:upAllDwon('+index+',\'down\')">↓↓</a></td>';
			bodystr +='</tr>';
			return bodystr;
		}
		
		/**
		 * 切换行到最后一个，第一个
		 * @param idpre 索引
		 * @param type 类型（上，下）
		 */
		function upAllDwon(idpre,type){
			var rownum = 0;
			if(type=="up"){
				//获取第一个行号
				if($excelArrays && $excelArrays.legnth!=0){
					rownum = 0;
				}else{
					rownum = $first;
				}
				baseUpDwon(rownum,idpre,type);
			}else if(type=="down"){
				//获取最后一个行号
				if($excelArrays && $excelArrays.legnth!=0){
					rownum = $excelArrays.length-1;
				}else{
					rownum = $last;
				}
				baseUpDwon(idpre,rownum,type);
			}
		}
		
		/**
		 * 切换行
		 * @param idpre 索引
		 * @param type 类型（上，下）
		 */
		function upDwon(idpre,type){
			if(type=="up"){
				baseUpDwon(idpre-1,idpre,type);
			}else if(type=="down"){
				baseUpDwon(idpre,idpre+1,type);
			}
		}
		
		/**
		 * 调换索引
		 * @param pre 前一个
		 * @param next 后一个
		 * @param type 类型
		 */
		function baseUpDwon(pre,next,type){
			var prehtml;
			var nexthtml;
			var preObject = $("#excel_d"+pre);
			var nextObject = $("#excel_d"+next);
			if(preObject[0] && nextObject[0]){
				prehtml = preObject.html().replace("upDwon("+(pre)+",'up')","upDwon("+(next)+",'up')")
				.replace("upAllDwon("+(pre)+",'up'","upAllDwon("+(next)+",'up'")
				.replace("upDwon("+(pre)+",'down')","upDwon("+(next)+",'down')")
				.replace("upAllDwon("+(pre)+",'down'","upAllDwon("+(next)+",'down'");
				nexthtml = nextObject.html().replace("upDwon("+(next)+",'up')","upDwon("+(pre)+",'up')")
									  .replace("upAllDwon("+(next)+",'up'","upAllDwon("+(pre)+",'up'")
									  .replace("upDwon("+(next)+",'down')","upDwon("+(pre)+",'down')")
									  .replace("upAllDwon("+(next)+",'down'","upAllDwon("+(pre)+",'down'");
				preObject.html(nexthtml);
				nextObject.html(prehtml);
			}
		}
		
		/**
		 * 全选，反选
		 */
		function clickCheckAll(){
			var cll = $("#checkAll").prop("checked");
			if(cll){
				$("input[name='check']").prop("checked",true);
			}else{
				$("input[name='check']").prop("checked",false);
			}
		}

		/**
		* 导出提交
		*/
		function exportSubmitExcelAll(){
			 $("#pageSize").val(99999999);
			 $("#pageNumber").val(1);
			 $("#exportButton").attr("disabled","disabled");
			 $("#exportButton").val("导出中...");
			 $("#exportButtonAll").attr("disabled","disabled");
			 $("#exportButtonAll").val("导出中...");
			 $("#exportSubmit")[0].submit();
			 $("#exportButton").attr("disabled",false);
			 $("#exportButton").val("导出本页");
			 $("#exportButtonAll").attr("disabled",false);
			 $("#exportButtonAll").val("导出所有");
		}
		
		/**
		 * 导出提交
		 */
		function exportSubmitExcel(){
			var pageSize = $(".pagination-page-list").val();
			var pageNumber = $(".pagination-num").val();
			 $("#pageSize").val(pageSize);
			 $("#pageNumber").val(pageNumber);
			$("#exportButton").attr("disabled","disabled");
			$("#exportButton").val("导出中...");
			$("#exportButtonAll").attr("disabled","disabled");
			$("#exportButtonAll").val("导出中...");
			$("#exportSubmit")[0].submit();
			$("#exportButton").attr("disabled",false);
			$("#exportButton").val("导出本页");
			$("#exportButtonAll").attr("disabled",false);
			$("#exportButtonAll").val("导出所有");
		}
		