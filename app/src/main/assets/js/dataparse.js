function expressDataParse(type, part, data, flag) {
	if (flag) {
		$("." + part).remove();
	}
	if (data.path.length > 0) {
		type.selectAll("." + part).data(data.path).enter().append("g").attr("class", function(g) {
			var e = d3.select(this);
			var f = e.append(g.type);
			g.style.forEach(function(h) { f.attr(h.attr, h.val) });
			return part;
		});
		if ((data.cX != 0) || (data.cY != 0)) {
			type.attr("transform", "translate(" + data.cX + "," + data.cY + ")");
		}
	}
}