//script/edit.js
/*
 * 笔记编辑界面js脚步
 */

//页面加载后获取登录用户的id
$(function(){
	var userId = getCookie("userId");
	console.log('userId:'+userId);
	listNotebooksAction(userId);
	//为保存按钮添加保存方法
	$('#save_note').click(savaNoteAction);
	//为添加笔记按钮绑定事件
	$('#add_note').click(addNoteAction);
	
});

var model = {
				notebooks:[],
				currentNotebook:{},
				notes:[],
				currentNote:{}
			};

function listNotebooksAction(userId){
//	$.ajax({
//		url:baseUrl+'/notebook/list.do?userId='+userId,
//		type:'get',
//		dataType:'json',
//		success:function(result){
//			if(result.state==SUCCESS){
//				var list = result.data;
//				for(var i=0;i<list.length;i++){
//					console.log(list[i]);
//				}
//			}else{
//				alert(result.data.message);
//			}
//		}
//	});

	$.getJSON(baseUrl+'/notebook/listNotebooks.do?userId='+userId,function(result){
			if(result.state==SUCCESS){
				var list = result.data;
//				for(var i=0;i<list.length;i++){
//					console.log(list[i]);
//				}
				//更新数据模型
				model.notebooks = list;
				//刷新显示
				paintNotebooks();
			}else{
				alert(result.message);
			}
	});
}
/*
 * 将数据模型model中的数据更行显示到界面上
	<li class="online">
		<a  class='checked'>
			<i class="fa fa-book" title="online" rel="tooltip-bottom"></i>
			默认笔记本
		</a>
	</li>
 */
function paintNotebooks(){
	var list = model.notebooks;
	var ul = $('#notebooks');
	//ul.empty();
	ul.html('');
	//var lihtml = "";
	for(var i=0;i<list.length;i++){
		var li = '<li class="online">'+
						'<a>'+
							'<i class="fa fa-book" title="online" rel="tooltip-bottom"></i>'+
							list[i].name+
						'</a>'+
				 '</li>';
		//lihtml += li;
		//为li添加点击事件
		li = $(li).data('index',i).click(function(){
			
			$(this).parent().find('a').removeClass('checked');
			$(this).children('a').addClass('checked');
			
			var index = $(this).data('index');
			//console.log(index);
			var notebook = list[index];
			model.currentNotebook = notebook;
			//console.log(notebook);
			listCurrentNotesAction();
		});
		ul.append(li);
		ul.children('li').eq(0).click();
	}
	//ul.html(lihtml);
}
/*
 * 控制器方法：获取数据，更新模型
 */
function listCurrentNotesAction(){
	var notebookId = model.currentNotebook.id;
	var url = baseUrl+'/notebook/note/listNotes.do?notebookId='+notebookId;
	//console.log(url);
	//发起异步请求获取notes
	$.getJSON(url,function(result){
		if(result.state==SUCCESS){
		//异步请求成功后更新model.notes
			//console.log(result.data);	
			model.notes = result.data;
			//调用 paintNotes
			paintNotes();
		}else{
			alert(result.message);
		}
	});
}
/*
 * 将数据模型中的notes显示到界面
 */
function paintNotes(){
	var list = model.notes;
	var ul = $('#notes').empty();
	for(var i=0;i<list.length;i++){
		//console.log(list);
		var li = '<li class="online">'+
					'<a>'+
						'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+
						 list[i].title+
						 '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down" disabled="disabled"><i class="fa fa-chevron-down"></i></button>'+
					'</a>'+
					'<div class="note_menu" tabindex="-1">'+
						'<dl>'+
							'<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>'+
							'<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>'+
							'<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>'+
						'</dl>'+
					'</div>'+
				'</li>';
		li = $(li).data('index',i);
		ul.append(li);
		//为li下的a绑定事件
		li.children('a').click(function(){
			//console.log($(this));
			//添加点击效果
			$(this).parent().parent().find('a').removeClass('checked');
			$(this).parent().parent().find('button').attr('disabled','disabled');
			$(this).parent().parent().find('.note_menu').slideUp('100');
			$(this).addClass('checked');
			$(this).children('button').removeAttr('disabled');
			var index = $(this).parent().data('index');
			model.currentNote = list[index];
			loadNoteAction(list[index].id);
			//阻止事件传播
			return false;
		});
		//为菜单按钮绑定事件
		li.find('button').click(function(){
			//console.log($(this));
			$(this).parent().next().slideToggle();
			return false;
		});
		
	}
	
	ul.find('a').eq(0).click();
}

function loadNoteAction(noteId){
	//console.log(noteId);
	var url = baseUrl+'/notebook/note/loadNote.do?noteId='+noteId;
	$('#input_note_title').val('loading...');
	$.getJSON(url,function(result){
		if(result.state==SUCCESS){
			model.currentNote = result.data;
			paintCurrentNote();
		}else{
			alert(result.message);
		}
	});
}
function paintCurrentNote(){
	var note = model.currentNote;
	var title = note.title;
	var body  = note.body;
	$('#input_note_title').val(title);
	um.setContent(body);
}

function savaNoteAction(){
	
	var content  = um.getContent();
	var title = $('#input_note_title').val();
	if(content==model.currentNote.body && title==model.currentNote.title){
		//如果笔记内容没有被编辑，就不向服务器发送更新笔记的请求
		return;
	}else{
		//保存笔记
		var data = {
			'noteId':model.currentNote.id,
			'title':title,
			'body':content
		};
		var url = baseUrl+'/notebook/note/saveNote.do';
		$('#sava_note').html('保存中...').attr('disabled','disabled');
		//将笔记信息发送给服务器
		$.post(url,data,function(result){
			if(result.state==SUCCESS){
				$('#sava_note').html('保存笔记').removeAttr('disabled');
				listCurrentNotesAction();
			}else{
				alert(result.message);
			}
		});
		
		
		
	}
}

function addNoteAction(){
	//弹出添加笔记的对话框
	var url = baseUrl+'/alert/alert_note.html';//对话框资源位置
	$('#can').load(url,function(response,status,xhr){
		if(status=='success'){
			//对话框上的按钮绑定事件
			$('#can .cancle,#can .close').click(function(){
				$('#can').empty();
			});
			$('#can .sure').click(addNote);
		}else{
			$('#can').empty();
		}
	});
}
function addNote(){
	//取得笔记名称
	var title = $('#input_note').val();
	//取得当前笔记本id
	var notebookId = model.currentNotebook.id;
	var data = {'title':title,'notebookId':notebookId,'userId':getCookie("userId")};
	console.log(data);
	var url = baseUrl+'/notebook/note/addNote.do';
	$.post(url,data,function(result){
		if(result.state==SUCCESS){
			//刷新笔记列表
			listCurrentNotesAction();
			$('#can .cancle').click();
		}else{
			alert(result.message);
		}
	});
	
}










	