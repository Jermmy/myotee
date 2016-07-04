function expressDataParse(type, part, data, flag) {
	if (flag) {
		$("." + part).remove();
	}
	console.log("type===>", type.selectAll("." + part));
	if (data.path.length > 0) {
		type.selectAll("." + part).data(data.path).enter().append("g").attr("class", function(g) {
			var e = d3.select(this);
			var f = e.append(g.type);
			g.style.forEach(function(h) { f.attr(h.attr, h.val) });
			return part;   // 这一步是将class的值设为part，方便下次更新时selectAll("."+part)能找到相应的标签
		});

		if ((data.cX != 0) || (data.cY != 0)) {
			type.attr("transform", "translate(" + data.cX + "," + data.cY + ")");
		}
	}
}

function expressHairDataParse(type, part, data, flag) {
	if (flag) {
		$("." + part).remove();
	}
	if (data.frontPath.length > 0) {
		type.selectAll("." + part).data(data.frontPath).enter().append("g").attr("class", function(g) {
			var e = d3.select(this);
			var f = e.append("path").attr("d", g.path);
			g.style.forEach(function(h) {f.attr(h.attr, h.val)} );
			return part;
		});
		if ((data.cX != 0) || (data.cY != 0)) {
			type.attr("transform", "translate(" + data.cX[0] + "," + data.cY[0] + ")");
		}
	}
}