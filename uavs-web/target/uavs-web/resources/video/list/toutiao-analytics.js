!function(e) {
    function t(e) {
        for (var t in e) return ! 1;
        return ! 0
    }
    function n() {
        S = document.createElement("iframe"),
        S.id = "__ToutiaoJSBridgeIframe_SetResult",
        S.style.display = "none",
        document.documentElement.appendChild(S),
        _ = document.createElement("iframe"),
        _.id = "__ToutiaoJSBridgeIframe",
        _.style.display = "none",
        document.documentElement.appendChild(_)
    }
    function r(e, t, n) {
        if (n = n || 1, e && "string" == typeof e) {
            var r = (I++).toString();
            "function" == typeof t && (w[r] = t);
            var o = {
                JSSDK: n,
                func: e,
                params: {},
                __msg_type: "call",
                __callback_id: r
            };
            b.push(o),
            _.src = E + "dispatch_message/"
        }
    }
    var o, a, i, s, u, c, l, d, g = 3,
    f = 2e3,
    p = !1,
    m = !1,
    v = !1,
    h = "advertiser",
    y = !1;
    _taq = _taq || [];
    var _, S, A = [],
    b = [],
    I = 1e3,
    w = {},
    E = "bytedance://";
    n(),
    e.getTTInfo = function(e) {
        var n = e.key,
        o = navigator.userAgent.match(/JSSDK\/([\d.]+)/i),
        a = o ? o[1] : 1,
        i = e.error,
        s = e.success,
        u = e.fail;
        r(n,
        function(e) {
            e = e || {};
            var n = e.code;
            t(e) ? n = 1 : void 0 == n && (n = 1),
            -1 == n && i && i(e),
            0 == n && u && u(e),
            1 == n && s && s(e)
        },
        a)
    };
    var x = {
        getUA: function() {
            var t = {
                ipad: /ipad/gi.test(navigator.appVersion),
                iphone: /iphone/gi.test(navigator.appVersion),
                android: /android/gi.test(navigator.userAgent),
                nexusS: -1 != e.navigator.userAgent.indexOf(/Nexus\s*S/gi),
                miui: /MiuiBrowser/i.test(navigator.userAgent),
                huaWei: /huawei/i.test(navigator.userAgent),
                safari: /Safari/i.test(navigator.userAgent) && /iPhone\sOS/.test(navigator.userAgent),
                weixin: /MicroMessenger/gi.test(navigator.userAgent),
                UC: /UC/i.test(navigator.userAgent) || /UC/i.test(navigator.appVersion),
                Baidu: /baidubrowser/i.test(navigator.userAgent) || /baiduboxapp/i.test(navigator.userAgent),
                QQ: /QQBrowser/i.test(navigator.userAgent)
            },
            n = "";
            for (var r in t) t[r] && (n = r);
            return n
        },
        isArray: function(e) {
            return "[object Array]" === Object.prototype.toString.call(e)
        },
        isToutiao: function() {
            var e = "http://nativeapp.toutiao.com" == document.referrer || /(News|NewsSocial|Explore|NewsArticle)( |\/)(\d.\d.\d)/i.test(navigator.userAgent);
            return e
        },
        getLocationParam: function(t) {
            var n = {
                QueryString: function(t) {
                    var n = e.location.href,
                    r = new RegExp("" + t + "=([^&?]*)", "ig");
                    return n.match(r) ? decodeURI(n.match(r)[0].substr(t.length + 1)) : ""
                }
            };
            return n.QueryString(t)
        },
        queryToJson: function(e) {
            for (var t, n, r, o, a = e.substr(e.lastIndexOf("?") + 1), i = a.split("&"), s = i.length, u = {},
            c = 0; s > c; c++) i[c] && (o = i[c].split("="), t = o[0], n = o[1], r = u[t], "undefined" == typeof r ? u[t] = n: this.isArray(r) ? r.push(n) : u[t] = [r, n]);
            return u
        },
        jsonToQuery: function(e) {
            var t = [];
            e = e || {};
            var n = /^(?:string|boolean|number)/i;
            for (var r in e) e.hasOwnProperty(r) && n.test(typeof e[r]) && t.push(r + "=" + e[r]);
            return t.join("&")
        },
        extend: function(e, t) {
            for (var n in t) e[n] = t[n]
        },
        getAdId: function() {
            var e = x.getLocationParam("_tt_ad_id") || x.getLocationParam("ad_id") || x.getLocationParam("aid");
            return e
        },
        encodeParams: function(e) {
            var t = [];
            e = e || {};
            var n = /^(?:string|boolean|number)/i;
            for (var r in e) if (e.hasOwnProperty(r) && n.test(typeof e[r])) {
                var o = decodeURIComponent(e[r]);
                t.push(encodeURIComponent(r) + "=" + encodeURIComponent(o))
            }
            return t.join("&")
        },
        jsonp: function(t) {
            if (t = t || {},
            !t.url || !t.callback) throw new Error("参数不合法");
            var n = ("jsonp_" + Math.random()).replace(".", ""),
            r = document.getElementsByTagName("head")[0];
            t.data[t.callback] = n;
            var o = this.encodeParams(t.data),
            a = document.createElement("script");
            r.appendChild(a),
            e[n] = function(o) {
                r.removeChild(a),
                clearTimeout(a.timer),
                e[n] = null,
                t.success && t.success(o)
            },
            a.src = t.url + "?" + o,
            t.time && (a.timer = setTimeout(function() {
                e[n] = null,
                r.removeChild(a),
                t.fail && t.fail({
                    message: "超时"
                })
            },
            t.time))
        },
        syncGet: function(e) {
            var t = new XMLHttpRequest;
            t.open("GET", e.url, !1),
            t.withCredentials = !0,
            t.send(null)
        },
        imageGet: function(t) {
            var n = new Image;
            n.src = e.location.protocol + t.url
        },
        addLoadEvent: function(t) {
            var n = e.onload;
            e.onload = "function" != typeof e.onload ? t: function() {
                n(),
                t()
            }
        },
        getSiteId: function() {
            var t = e.location.pathname,
            n = /\d+/g,
            r = t.match(n) || [];
            return r[0] || ""
        },
        setToSessionStorage: function(t, n) {
            var r = e.sessionStorage;
            r.setItem(t, n)
        },
        getFromSessionStorage: function(t) {
            var n = e.sessionStorage;
            return n.getItem(t)
        }
    },
    C = {
        getChildrenIndex: function(e) {
            if (e.sourceIndex) return e.sourceIndex - e.parentNode.sourceIndex - 1;
            for (var t = 0; e = e.previousElementSibling;) t++;
            return t
        },
        getXPath: function(e) {
            for (var t = ""; e.length;) {
                var n = Array.prototype.pop.apply(e);
                n && n.tagName && "body" != n.tagName.toLowerCase() && "html" != n.tagName.toLowerCase() && (t += n.tagName.toLowerCase() + this.getChildrenIndex(n) + (0 == e.length ? "": ">"))
            }
            return t
        },
        getElementByAttr: function(e, t) {
            if ("string" == typeof e && (e = document.getElementById(e)), e || (e = document), e.querySelectorAll) {
                var n = e.querySelectorAll("[" + t + "]");
                return n = Array.prototype.slice.call(n)
            }
            for (var r = e.getElementsByTagName("*"), o = r.length, a = 0, i = []; o > a; a++) {
                var s = r[a];
                s.getAttribute(t) && i.push(s)
            }
            return i
        },
        getPerformanceObj: function() {
            if (!e.performance) return "";
            var t = performance.timing;
            return t
        },
        getDomCount: function() {
            return document.getElementsByTagName("*").length
        },
        getElementParentsAndSelf: function(e) {
            var t = this.getElementParents(e);
            return t.unshift(e),
            t
        },
        getElementParents: function(e) {
            for (var t = []; e;) e = e.parentNode,
            e && t.push(e);
            return t
        },
        delegate: function(t, n, r, o) {
            function a(n) {
                var a = /^\[(.*)\]$/,
                s = e.event ? e.event: n,
                u = s.target || s.srcElement;
                if (a.test(r)) for (var c = r.match(a)[1], l = C.getElementByAttr(t, c), d = u; d;) {
                    if (i.inElement(d, l)) {
                        o.call(u, s);
                        break
                    }
                    d = d.parentNode
                }
            }
            if ("string" == typeof t && (t = document.getElementById(t)), t || (t = document), r && "function" == typeof o) {
                var i = this;
                t[n] = a
            }
        },
        inElement: function(e, t) {
            for (var n = t.length,
            r = 0; n > r; r++) if (e == t[r]) return ! 0;
            return ! 1
        }
    },
    P = {
        create: function(e, t) {
            e || console.log("create error"),
            h = "accounter",
            o = e,
            t = t
        },
        send: function(e) {
            if (e.etype && "pv" != e.etype && !e.target) return void console.log("error: no target param");
            var t = {},
            n = this;
            for (var r in e) if ("etype" == r) t.event_type = e[r];
            else if ("target" == r) {
                var o = n.getPosition(e[r]);
                x.extend(t, o)
            } else t[r] = e[r];
            return A.length < g ? void A.push(t) : void this.consumeQueue()
        },
        transferQueue: function(e) {
            for (var t = this; _taq.length;) {
                var n = _taq.shift();
                if ("create" == n[0]) h = "accounter",
                o = n[1] || "",
                a = n[2] || "",
                o || console.log("error: accountId is null"),
                a || console.log("error: domain is null");
                else if ("trackinit" == n[0]) s = n[1] || "",
                u = n[2] || "",
                customSteps = n[3] || "",
                customSteps && !isNaN(customSteps - 0) && (g = customSteps - 0),
                s || console.log("error: device is null"),
                u || console.log("error: app is null");
                else if ("trackevent" == n[0]) {
                    var r = n[1] || "",
                    i = n[2] || "",
                    c = n[3] || "",
                    l = n[4] || "",
                    d = n[5] || "";
                    r || console.log("error: page is null"),
                    i || console.log("error: etype is null"),
                    c || console.log("error: concrete is null"),
                    "pv" == i && (y = !0),
                    "pv" == i || l || console.log("error: target is null");
                    var f = {
                        page: r,
                        event_type: i,
                        concrete: c,
                        option: d
                    };
                    if (l) {
                        var p = t.getPosition(l);
                        x.extend(f, p)
                    }
                    A.push(f)
                } else "debug" == n[0] ? v = !0 : console.log("push type error")
            }
            return e ? void this.consumeQueue() : void(A.length >= g ? this.consumeQueue() : v && console.log("left_info: " + JSON.stringify(A) + "\nlength: " + A.length))
        },
        consumeQueue: function() {
            var e = A.length;
            if (! (0 >= e)) {
                var t = this.getDeviceInfo(),
                n = JSON.stringify(A);
                if (n.length < f) x.extend(t, {
                    track_data: n
                }),
                t.ad_id = x.getAdId(),
                this.sendLog(t),
                A = [];
                else {
                    for (var r = []; A.length;) {
                        var o = A.shift();
                        r.push(o);
                        var a = JSON.stringify(r);
                        a.length > f && (x.extend(t, {
                            track_data: a
                        }), t.ad_id = x.getAdId(), this.sendLog(t), r = [])
                    }
                    r.length && (x.extend(t, {
                        track_data: JSON.stringify(r)
                    }), t.ad_id = x.getAdId(), this.sendLog(t))
                }
            }
        },
        sendLog: function(e) {
            var t = {
                advertiser: "//isub.snssdk.com/2/wap/landing_page_log/",
                accounter: "//isub.snssdk.com/api/2/wap/account_log/"
            };
            x.jsonp({
                url: t[h],
                callback: "callback",
                data: e,
                success: function() {
                    console.log("send log success")
                },
                fail: function() {
                    console.log("send log fail")
                }
            })
        },
        doConsume: function(e) {
            p = !0,
            clearTimeout(d),
            "success" == e ? this.transferQueue() : "timeout" == e ? this.transferQueue() : console.log("getAppInfo fail")
        },
        getPerformance: function() {
            m = !0,
            i = C.getPerformanceObj()
        },
        sendPerformance: function() {
            var e = this.getDeviceInfo();
            x.extend(e, i),
            e.dom_count = C.getDomCount(),
            e.ad_id = x.getAdId()
        },
        getDeviceInfo: function() {
            var e = {};
            return o && (e.account_id = o),
            a && (e.domain = a),
            s && (e.device = s),
            u && (e.app = u),
            c && (e.device_id = c),
            l && (e.user_id = l),
            e
        },
        getPosition: function(t) {
            if (t) {
                var n = e.innerWidth || document.body.clientWidth,
                r = t.offsetLeft,
                o = t.offsetHeight,
                a = 320,
                i = C.getElementParents(t),
                s = {
                    ox: r,
                    oy: o,
                    tx: ~~ (r * a / n),
                    ty: ~~ (o * a / n),
                    xpath: C.getXPath(i)
                };
                return s
            }
        }
    };
    C.delegate(document, "onmousedown", "[tt-data-click]",
    function() {
        $target = this;
        var e = $target.getAttribute("tt-data-click");
        e.indexOf("etype") || (e += "&etype=click"),
        v && console.log("click_info: " + e),
        P.send(x.queryToJson(e))
    }),
    _taq.pushListener = P.transferQueue;
    var N = _taq.push;
    _taq.push = function() {
        N.apply(this, arguments),
        "function" == typeof this.pushListener && p && this.pushListener.call(P)
    },
    x.isToutiao() && getTTInfo({
        key: "appInfo",
        success: function(e) {
            "string" == typeof e && (e = JSON.parse(e)),
            c = e.device_id || "",
            l = e.user_id || "",
            P.doConsume("success")
        },
        fail: function() {
            P.doConsume("fail")
        },
        error: function() {
            P.doConsume("error")
        }
    }),
    d = setTimeout(function() {
        P.doConsume("timeout")
    },
    1500),
    e.onbeforeunload = function() {
        if (y) P.transferQueue(!0);
        else {
            var e = C.getElementByAttr(document, "tt-data-view"),
            t = "";
            t = e.length ? e[0].getAttribute("tt-data-view") : document.title,
            _taq.push(["trackevent", location.href, "pv", "", "", {
                title: t
            }])
        }
    },
    e.ta = P
} (window, document);