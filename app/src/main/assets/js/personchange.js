// 脸部编辑操作模块
function faceChange(i) {
	expressDataParse(face, "facePart", faceData["face"+i].frontSide, true);
	if (faceTag.color != "") {
		roleA.selectAll(".faceColor").attr("fill", faceTag.color);
	}
}

// 头发编辑操作模块
function hairChange(i) {
	expressHairDataParse(hair, "hairPart", hairData["hair"+i].frontSide, true);
	if (hairTag.color != "") {
		roleA.selectAll(".hairColor").attr("fill", hairTag.color);
	}
}

// 眼睛编辑操作模块
function eyeChange(i) {
	expressDataParse(eye, "eyePart", eyeData["eye"+i].frontSide, true);
}

// 眉毛编辑操作模块
function eyebrowChange(i) {
	expressDataParse(eyebrow, "eyebrowPart", eyebrowData["eyebrow"+i].frontSide, true);
}

// 嘴巴编辑操作模块
function mouthChange(i) {
	expressDataParse(mouth, "mouthPart", mouthData["mouth"+i].frontSide, true);
}