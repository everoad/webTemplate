class Menu {

	constructor() {
	    this.menu_fir = [];
	    this.delFirArr = [];
	    this.delSecArr = [];
	    
	    this.selectedArr = [];
	    this.selectedTitle = '';
	    
	    this.addIndex = 0;
	    this.flag = -1;
	    
	    this.getUrl = '/admin/menu/list'
	    this.sendUrl = '/admin/menu'	

	    this.list = document.getElementById('list');
	    this.titleElem = document.getElementById('title');
	    this.radioBox = document.getElementById('radiobox');

	    document.getElementById('addBtn').addEventListener('click', (e) => this.add(e));
	    document.getElementById('cplteBtn').addEventListener('click', (e) => this.cplte());

	    this.titleElem.addEventListener('input', (e) => this.edit(e, 'name'));

	    this.init();
	}

	
	
	init() {
		$.get(this.getUrl)
			.done((datas) => {
				console.log(datas)
				this.menu_fir = datas;
				this.mkList();
				$('#list').sortable({
					delay: 200,
					update: (event, ui) => {
						let exfirArr = [], i = 0;
						for(let idx of $( "#list" ).sortable( "toArray" )) {
							exfirArr[i] = this.menu_fir[idx];
							exfirArr[i++].index = i;
						}
						this.menu_fir = exfirArr;
						this.mkList();
					}
				});
			}).fail(() => {alert('fail');});
	}

	
	
	
	mkList() {
		let body = '';
		for(let i in this.menu_fir) {
			let fir = this.menu_fir[i];
			body += '<li id="'+i+'">'
						+	'<div class="title">'
						+		'<span id="firTitle-'+i+'">' + fir.name + '</span>'
						+ 		'<button id="firDelBtn-'+i+'" class="btn btn-default btn-sm">－</button>'
						+ 	 	'<button id="firAddBtn-'+i+'" class="btn btn-default btn-sm">＋</button>'
						+	'</div>'
						+  '<ul id="list-'+i+'">';
			for(let j in fir.menu_sec) {
				let sec = fir.menu_sec[j];
				body +=	'<li id="'+j+'">'
							+		'<div class="title">'
							+			'<span id="secTitle-'+i+'-'+j+'">' + sec.name + '</span>'
							+			'<button id="secDelBtn-'+i+'-'+j+'" class="btn btn-default btn-sm">－</button>'
							+		'</div>'
							+  '</li>';
			}
			body += 	'</ul>'
					+ 	 '</li>';
		}
		this.list.innerHTML = body;

		for(let i in this.menu_fir) {
			document.getElementById('firAddBtn-'+i).addEventListener('click', (e) => this.add(e, i));
			document.getElementById('firDelBtn-'+i).addEventListener('click', (e) => this.del(this.menu_fir, this.delFirArr, i, e));
			document.getElementById('firTitle-'+i).addEventListener('click', (e) => this.slct(this.menu_fir, e, i));
			for(let j in this.menu_fir[i].menu_sec) {
				document.getElementById('secDelBtn-'+i+'-'+j).addEventListener('click', (e) => this.del(this.menu_fir[i].menu_sec, this.delSecArr, j, e));
				document.getElementById('secTitle-'+i+'-'+j).addEventListener('click', (e) => this.slct(this.menu_fir[i].menu_sec, e, j));
			}
			$('#list-' + i).sortable({
				delay: 200,
				update: (event, ui) => {
					let exSecArr = [], j = 0;
					for(let idx of $('#list-' + i).sortable( "toArray" )) {
						exSecArr[j] = this.menu_fir[i].menu_sec[idx];
						exSecArr[j++].index = j;
					}
					this.menu_fir[i].menu_sec = exSecArr;
					this.mkList();
				}
			});
		}
	}

	
	
	add(e, i) {
		if(e.target.id === 'addBtn') {
			this.menu_fir.push({ menu_fir_seq: 'new' + this.addIndex++, name: 'new1', type: 'grid', index: this.menu_fir.length + 1, menu_sec: [] });
		} else {
			var sec = this.menu_fir[i].menu_sec;
			sec.push({ menu_sec_seq: 'new', name: 'new2', type: 'game', index: sec.length + 1, menu_fir_seq: this.menu_fir[i].menu_fir_seq});
		}
			this.mkList();
	}

	
	
	slct(targetArr, e, i) {
		this.switchRightBox(e);
		this.titleElem.value = targetArr[i].name;
		this.typeElem.value = targetArr[i].type;
		this.activeElem.value = targetArr[i].active;
		this.selectedArr = targetArr[i];
		this.selectedTitle = e.target;
		this.flag = i;
	}

	
	
	edit(e, field) {
		if(this.flag === -1) return;
		this.selectedArr[field] = e.target.value;
		if(field == 'name')
			this.selectedTitle.innerHTML = e.target.value;
	}

	
	
	del(targetArr, delArr, i, e) {
		delArr.push(targetArr.splice(i, 1)[0]);
		e.target.parentElement.parentElement.remove();
	}
	
	
	
	cplte() {
		let data = JSON.stringify({
			menu_fir: this.menu_fir,
			del_menu_fir: this.delFirArr,
			del_menu_sec: this.delSecArr
		});
	
		$.ajax({
			url: this.sendUrl,
			type: 'post',
			data: data,
			dataType: 'json',
			processData : true, 
			contentType : "application/json; charset=UTF-8",
			success: (data) => {	location.reload();},
			error: () => {alert('fail');}
		});
	}
	
	switchRightBox(e) {
		let body = '';
		if(e.target.id.includes('fir')) {
			body += '<label class="radio"><input type="radio"  name="type" value="grid"><i class="rounded-x"></i>그리드</label>'
			body += '<label class="radio"><input type="radio" name="type" value="list"><i class="rounded-x"></i>일반</label><br><br>'
			body +='<label class="radio"><input type="radio"  name="active" value="active"><i class="rounded-x"></i>활성</label>'
			body +='<label class="radio"><input type="radio"  name="active" value="non-active"><i class="rounded-x"></i>비활성</label><br><br>'
		} else {
			body += '<label class="radio"><input type="radio"  name="type" value="game"><i class="rounded-x"></i>게임</label>'
			body += '<label class="radio"><input type="radio"   name="type" value="entertainer"><i class="rounded-x"></i>연예인</label>'
			body += '<label class="radio"><input type="radio"  name="type" value="sports"><i class="rounded-x"></i>스포츠</label><br><br>'
			body +='<label class="radio"><input type="radio"   name="active" value="active"><i class="rounded-x"></i>활성</label>'
			body +='<label class="radio"><input type="radio"  name="active" value="non-active"><i class="rounded-x"></i>비활성</label><br><br>'
		}
		this.radioBox.innerHTML = body;

		this.typeElem = document.form.type;
		for(let input of this.typeElem) {
			input.addEventListener('change', (e) => this.edit(e, 'type'));
		}
		this.activeElem = document.form.active;
		for(let input of this.activeElem) {
			input.addEventListener('change', (e) => this.edit(e, 'active'));			
		}
	}
}

new Menu();
