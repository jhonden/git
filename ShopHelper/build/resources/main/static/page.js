function Page(o){
	var moreType = o.moreType?o.moreType:'replace';
	var pageId = o.pageId?parseInt(o.pageId):1;
	var pageSize = o.pageSize ? o.pageSize : 10;
	var containerId = o.containerId;
	var url = o.url;
	var conditions = o.conditions?o.conditions:'';
	var mapRow = o.mapRow?o.mapRow:function(){};
	var before = o.before?o.before:function(){};
	var after = o.after?o.after:function(){};
	var page = {};
	this.minPage = 1;
	this.maxPage = 1;
	var context = this;
	
	/**
	 * 加载数据
	 */
	function getData(){
		//before
		before();
		//ajax加载数据
		$.ajax({
			url:url,
			type:'post',
			data:"pageId="+pageId+"&pageSize="+pageSize+conditions,
			success:function(msg){
				page = msg;
				var rows = "";
				for(var i=0, len=page.datas.length; i<len; i++){
					var rowid = (pageId-1)*pageSize+i;
					rows+=mapRow(page.datas[i],rowid,i);
				}
				if(moreType == "append"){
					$("#"+containerId).append(rows);
				}else{
					$("#"+containerId).html(rows);
				}
				after(context);
			},
			error:function(){
				//alert("加载失败");
			}
		});
	};
	
	/**
	 * 初始化
	 */
	this.init = function(){
		getData();
		return this;
	};
	
	/**
	 * 获得当前页
	 */
	this.getPageId = function(){
		return pageId;
	};
	
	this.setPageId = function(id){
		pageId = id;
	};
	
	this.setPageSize = function(size){
		pageSize = size;
	};
	
	/**
	 * 获得页数
	 */
	this.getPageCount = function(){
		return page.pageCount;
	};
	
	/**
	 * 设置查询条件
	 */
	this.setConditions = function(c){
		conditions = c;
		return this;
	};
	
	/**
	 * 获得记录行数
	 */
	this.getRows = function(){
		return page.rows;
	};
	
	/**
	 * 获得数据
	 */
	this.getDatas = function(){
		return page.datas;
	};
	
	/**
	 * 通过下表获得数据
	 */
	this.getDataByIndex = function(i){
		return page.datas[i];
	};
	
	/**
	 * 跳转
	 */
	this.goPage = function(go){
		if(go >= 1 && go <= page.pageCount){
			pageId = go;
			getData();
		}
		return this;
	};
	
	/**
	 * 下一页
	 */
	this.nextPage = function(){
		if(pageId+1 <= page.pageCount){
			pageId = pageId+1;
			getData();
		}
		return this;
	};
	
	/**
	 * 上一页
	 */
	this.prePage = function(){
		if(pageId - 1 >= 1){
			pageId = pageId-1;
			getData();
		}
		return this;
	};
	
	/**
	 * 刷新数据
	 */
	this.refreshPage = function(){
		getData();
		return this;
	};
	
	/**
	 * 首页
	 */
	this.firstPage = function(flag){
		if(flag==true || pageId != 1){
			pageId = 1;
			getData();
		}
		return this;
	};
	
	/**
	 * 末页
	 */
	this.lastPage = function(flag){
		if(flag==true || pageId != page.pageCount){
			pageId=page.pageCount;
			getData();
		}
		return this;
	};
};