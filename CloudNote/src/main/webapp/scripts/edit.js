//script/edit.js
/*
 * 笔记编辑界面js脚步
 */

//页面加载后获取登录用户的id
$(function(){
	var userId = getCookie("userId");
	model.userId = userId;
	console.log('userId:'+userId);
	listNotebooksAction(userId);
	//为保存按钮添加保存方法
	$('#save_note').click(saveNoteAction);
	//为添加笔记按钮绑定事件
	$('#add_note').click(addNoteAction);
	//为添加笔记本按钮绑定事件
	$('#add_notebook').click(addNotebookAction);
	//为退出登录按钮绑定事件
	$('#logout').click(function(){
		$(this).attr('href',baseUrl+'/account/logout.do')
		$.cookie('userId',null);
		$.cookie('userName',null);
		$.cookie('userNick',null);
	});
	
	$('#input_note_title').blur(function(){
		if($.isEmptyObject(model.currentNote)){
			alert('请选择一个笔记！');
		}
	});
});

 //重写JS原生alert函数
window.alert=function(e){
	$('#can').load('./alert/alert_error.html',function(){
		$('#error_info').text(' '+e);
		$('.opacity_bg').fadeIn();
		$('#can .cancle,#can .close').click(function(){
			$('#can div').fadeOut('500',function(){
				$('.opacity_bg').fadeOut();
				$('#can').empty();
			});
		});
		$('#can div').fadeIn('1000');
	});
}

var model = {
				userId:{},
				notebooks:[],
				currentNotebook:{},
				notebookIndex:0,
				notes:[],
				currentNote:{},
				noteIndex:0
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

	$.getJSON(baseUrl+'/notebook/listNotebooks.do?userId='+userId+'&nocache='+new Date().getTime(),function(result){
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
			model.notebookIndex=index;
			
			model.currentNote={};
			model.noteIndex=0;
			
			//切换笔记本时自动保存正在编辑的笔记
			//saveNoteAction();
			
			
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
	var url = baseUrl+'/notebook/note/listNotes.do?notebookId='+notebookId+'&nocache='+new Date().getTime();
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
	//console.log(list);
	var ul = $('#notes').empty();
	for(var i=0;i<list.length;i++){
		//console.log(list);
		var li = '<li class="online" style="display:none;">'+
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
			
			//切换笔记时自动保存正在编辑的笔记
			saveNoteAction();
			
			//console.log($(this));
			//添加点击效果
			$(this).parent().parent().find('a').removeClass('checked');
			$(this).parent().parent().find('button').attr('disabled','disabled');
			$(this).parent().parent().find('.note_menu').slideUp('100');
			$(this).addClass('checked');
			$(this).parent().find('button').removeAttr('disabled');
			var index = $(this).parent().data('index');
			model.currentNote = list[index];
			model.noteIndex=index;
			loadNoteAction(list[index].id);
			//阻止事件传播
			return false;
		});
		//为菜单按钮绑定事件
		li.find('.btn_slide_down').click(function(){
			//console.log($(this));
			$(this).parent().next().slideToggle();
			return false;
		});
		
		//为删除笔记按钮添加绑定事件
		li.find('.btn_delete').click(deleteNoteAction);
		//为移动笔记按钮绑定事件
		li.find('.btn_move').click(moveNoteAction);
		
		var note = list[i];
		if(model.currentNote.id){
			if(note.id==model.currentNote.id){
				li.children('a').addClass('checked');
			}
		}
	}
	ul.children('li').slideDown('slow');
	//ul.find('a').eq(0).click();
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

function saveNoteAction(){
	
	var content  = um.getContent();
	var title = $('#input_note_title').val();
	//console.log(model.currentNote);
	if($.isEmptyObject(model.currentNote)){
		return;
	}
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
				//listCurrentNotesAction();
				//更新model
				var note = result.data;
				model.currentNote=note;
				model.notes[model.noteIndex].title=note.title;
				paintNotes();
				paintCurrentNote();
			}else{
				alert(result.message);
			}
		});
		
		
		
	}
}
/*
 * 添加笔记的请求
 */
function addNoteAction(){
	alertDialog('/alert/alert_note.html',addNote);
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
			$('#can .close').click();
			//刷新笔记列表
			//listCurrentNotesAction();
			var note = result.data;
			model.currentNote=note;
			model.noteIndex=0;
			var ary = [{id:note.id,title:note.title}];
			model.notes = ary.concat(model.notes);
			paintNotes();
			paintCurrentNote();
			
		}else{
			//console.log(result.message);
			alert(result.message);
		}
	});
	
}

/*
 * 封装弹出对话框的请求
 */
 function alertDialog(url,sure){
 	//var sourceurl = baseUrl+url+'?nocache='+new Date().getTime();//对话框资源位置
 	var sourceurl = baseUrl+url;
	$('#can').load(sourceurl,function(response,status,xhr){
		if(status=='success'){
			//对话框上的按钮绑定事件
			$('#can .cancle,#can .close').click(function(){
				$('#can div').fadeOut('500',function(){
					$('#can').empty();
				});
			});
			$('#can .sure').click(sure);
			$('#can div').fadeIn('1000');
		}else{
			$('#can').empty();
		}
		if(url=='/alert/alert_move.html'){
			$('#can').find('h4').html('移动笔记:'+model.currentNote.title);
			var notebooks = model.notebooks;
			var list = $('#moveSelect').empty();
			for(var i=0;i<notebooks.length;i++){
				var notebook = notebooks[i];
				list.append($('<option>').val(notebook.id).text(notebook.name));
			}
		}
	});
 }


/*
 * 删除笔记的请求处理
 */
 function deleteNoteAction(){
 	var note = model.currentNote;
 	var index = model.noteIndex;
 	var next = index;
 	var notes = model.notes;
 	var notesLength = notes.length;
 	var url = baseUrl+'/notebook/note/deleteNote.do?noteId='+note.id;
 	$('#notes').children('li').eq(index).slideUp('200',function(){
	 	$.getJSON(url,function(result){
	 		if(result.state==SUCCESS){
			 	//console.log('hello');
	 			//更新数据模型
//	 			for(var i=index;i<notes.length-1;i++){
//	 				notes[i]=notes[i+1];
//	 			}
//	 			notes.pop();
				notes.splice(index,1);
	 			paintNotes();
	 			paintCurrentNote();
	 			if(index==notesLength-1){
	 				next -= 1;
	 			}
	 			$('#notes').children('li').find('a').eq(next).click();
	 		}else{
	 			alert(result.message);
	 		}
	 	});
 	});
 	return false;
 }
 
 /*
  * 添加笔记本的请求
  */
function addNotebookAction(){
	var userId = model.userId;
	var dialogUrl = '/alert/alert_notebook.html';
	alertDialog(dialogUrl,function(){
		var notebookName = $('#input_notebook').val();
		var data = {'notebookName':notebookName,'userId':userId};
		var url = baseUrl+'/notebook/addNotebook.do';
		//console.log(data);
		$.post(url,data,function(result){
			if(result.state==SUCCESS){
				$('#can .close').click();
				var notebook = result.data;
				model.currentNotebook = notebook;
				model.notebookIndex = 0;
				var ary = [notebook];
				model.notebooks= ary.concat(model.notebooks);
				paintNotebooks();
				
			}else{
				alert(result.message);
			}
		});
	});
}

function moveNoteAction(){
	var sourceUrl = '/alert/alert_move.html';
	alertDialog(sourceUrl,function(){
		var noteId = model.currentNote.id;
		var index = model.noteIndex;
		var toNotebookId = $('#can select').val();
		var notesLength = model.notes.length;
		//if()
		var data = {'noteId':noteId,'toNotebookId':toNotebookId};
		var url = baseUrl+'/notebook/note/moveNote.do';
		$.post(url,data,function(result){
			$('#can').find('.close').click();
			$('#notes').children('li').eq(index).slideUp('200',function(){
				if(result.state==SUCCESS){
					model.notes.splice(index,1);
					paintNotes();
		 			if(model.notes.length==0){
		 				$('#input_note_title').val('');
		 				um.setContent('');
		 				model.currentNote={};
		 			}
		 			if(index==notesLength-1){
		 				index -= 1;
		 			}
		 			$('#notes').children('li').find('a').eq(index).click();
				}else{
					alert(result.message);
				}
			});
		});
	});
	return false;
}








	