!function(e) {
	function t(r) {
		if (n[r])
			return n[r].exports;
		var o = n[r] = {
			exports : {},
			id : r,
			loaded : !1
		};
		return e[r].call(o.exports, o, o.exports, t), o.loaded = !0, o.exports
	}
	var n = {};
	return t.m = e, t.c = n, t.p = "//s3.pstatp.com/growth/fe_sdk/", t(0)
}([
		function(e, t, n) {
			e.exports = n(3)
		},
		,
		,
		function(e, t, n) {
			"use strict";
			!function(e, t) {
				var n = e.ttBannerConf && e.ttBannerConf.version;
				n = n || "1.0.40";
				var r, o = "//s3.pstatp.com/growth/fe_sdk/bannersdk/";
				r = n ? o + "sdk_" + n + ".js" : o + "sdk.js";
				var s = document.createElement("script");
				if (s.src = r, e.ttBannerConf && e.ttBannerConf.sdkid) {
					var d = e.ttBannerConf.sdkid;
					t.getElementById(d).parentNode.insertBefore(s, t
							.getElementById(d).nextSibling)
				} else
					t.getElementsByTagName("body")[0].appendChild(s)
			}(window, document)
		} ]);