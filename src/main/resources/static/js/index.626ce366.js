(function(e){function t(t){for(var n,a,o=t[0],l=t[1],u=t[2],c=0,p=[];c<o.length;c++)a=o[c],Object.prototype.hasOwnProperty.call(i,a)&&i[a]&&p.push(i[a][0]),i[a]=0;for(n in l)Object.prototype.hasOwnProperty.call(l,n)&&(e[n]=l[n]);d&&d(t);while(p.length)p.shift()();return s.push.apply(s,u||[]),r()}function r(){for(var e,t=0;t<s.length;t++){for(var r=s[t],n=!0,o=1;o<r.length;o++){var l=r[o];0!==i[l]&&(n=!1)}n&&(s.splice(t--,1),e=a(a.s=r[0]))}return e}var n={},i={index:0},s=[];function a(t){if(n[t])return n[t].exports;var r=n[t]={i:t,l:!1,exports:{}};return e[t].call(r.exports,r,r.exports,a),r.l=!0,r.exports}a.m=e,a.c=n,a.d=function(e,t,r){a.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:r})},a.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},a.t=function(e,t){if(1&t&&(e=a(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var r=Object.create(null);if(a.r(r),Object.defineProperty(r,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var n in e)a.d(r,n,function(t){return e[t]}.bind(null,n));return r},a.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return a.d(t,"a",t),t},a.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},a.p="/";var o=window["webpackJsonp"]=window["webpackJsonp"]||[],l=o.push.bind(o);o.push=t,o=o.slice();for(var u=0;u<o.length;u++)t(o[u]);var d=l;s.push([0,"chunk-vendors"]),r()})({0:function(e,t,r){e.exports=r("df31")},"3ed8":function(e,t,r){},"3f70":function(e,t,r){},6589:function(e,t,r){"use strict";r("f426")},"6d9f":function(e,t,r){"use strict";r("3f70")},"7c15":function(e,t,r){"use strict";r.d(t,"a",(function(){return n}));var n=""},8229:function(e,t,r){"use strict";r("3ed8")},cf05:function(e,t,r){e.exports=r.p+"static/img/logo.62d5a428.png"},d672:function(e,t,r){},df31:function(e,t,r){"use strict";r.r(t);r("e260"),r("e6cf"),r("cca6"),r("a79d");var n=r("2b0e"),i=r("8c4f"),s=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{attrs:{id:"app"}},[n("el-container",[n("el-header",[n("el-menu",{staticClass:"el-menu-demo",attrs:{"default-active":e.activeIndex,mode:"horizontal",router:!0}},[n("el-menu-item",[n("el-image",{staticClass:"logo",attrs:{src:r("cf05"),fit:"cover"}})],1),n("el-menu-item",{attrs:{index:"/"}},[e._v("首页")]),n("el-menu-item",{attrs:{index:"/history"}},[e._v("测速记录")]),n("el-submenu",{staticClass:"user",attrs:{index:""}},[n("template",{slot:"title"},[e._v(e._s(e.username))]),n("el-menu-item",[n("el-link",{attrs:{type:"danger",underline:!1},on:{click:e.logout}},[e._v("退出登录")])],1)],2)],1)],1),n("el-main",{staticClass:"main"},[n("router-view")],1),n("el-footer")],1)],1)},a=[],o=r("bc3a"),l=r.n(o),u=r("7c15"),d={name:"App",methods:{logout:function(){var e=this;this.appData.ENABLE_CAS_LOGIN&&(window.location.href=this.appData.CAS_CENTER_ADDRESS+this.appData.CAS_LOGOUT_PATH),l.a.delete(u["a"]+"/api/user/session").then((function(t){var r=t.data;r.status?window.location.href="/":e.$message.error(r.data.message)})).catch((function(t){console.error(t),e.$message.error("退出登录失败")}))}},mounted:function(){var e=this;l.a.get(u["a"]+"/api/config/appinfo").then((function(t){var r=t.data;r.status?(e.appData=r.data,e.appData.WEBAPP_NAME&&(document.title=e.appData.WEBAPP_NAME)):e.$message.error(r.data.message)})).catch((function(t){console.error(t),e.$message.error("应用信息加载失败")})),l.a.get(u["a"]+"/api/user/logined").then((function(t){var r=t.data;r.status?r.data.username&&(e.username=r.data.username):e.$message.error(r.data.message)})).catch((function(t){console.error(t),e.$message.error("用户信息加载失败")}))},data:function(){return{activeIndex:"/",title:"xss",appData:{},username:"请登录"}},components:{}},c=d,p=(r("df64"),r("2877")),h=Object(p["a"])(c,s,a,!1,null,"7cc84773",null),f=h.exports,g=r("5c96"),m=r.n(g),v=(r("0fae"),function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("Speedtest",{staticClass:"speedtest"})}),_=[],b=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{attrs:{id:"testWrapper"}},[r("el-row",{attrs:{type:"flex",justify:"space-around"}},[r("el-col",{attrs:{span:6}},[r("el-button",{staticClass:"startButton",attrs:{type:e.startButtonType,disabled:e.startButtonForbidden},on:{click:e.startStop}},[e._v(e._s(e.startButtonStart?"结束":"开始"))])],1)],1),r("el-row",{staticClass:"serverListArea",attrs:{type:"flex",justify:"space-around"}},[r("el-col",{attrs:{span:16}},[r("el-form",[r("el-form-item",{attrs:{label:"测速节点列表:"}},[r("el-select",{attrs:{placeholder:"请选择"},on:{change:e.changeServer},model:{value:e.changeServerId,callback:function(t){e.changeServerId=t},expression:"changeServerId"}},e._l(e.serverLists,(function(e){return r("el-option",{key:e.id,attrs:{value:e.id,label:e.name}})})),1)],1)],1)],1)],1),r("el-row",{staticClass:"resNumberArea",attrs:{type:"flex",justify:"space-around"}},[r("el-col",{attrs:{span:9}},[r("div",{staticClass:"testName"},[e._v("Ping")]),r("div",{staticClass:"meterText",staticStyle:{color:"#aa6060"},attrs:{id:"pingText"}},[e._v(" "+e._s(e.pingText)+" ms ")])]),r("el-col",{attrs:{span:9}},[r("div",{staticClass:"testName"},[e._v("Jitter")]),r("div",{staticClass:"meterText",staticStyle:{color:"#aa6060"},attrs:{id:"jitText"}},[e._v(" "+e._s(e.jitterText)+" ms ")])])],1),r("el-row",{staticClass:"resNumberArea",attrs:{type:"flex",justify:"space-around"}},[r("el-col",{staticClass:"testArea"},[r("div",{staticClass:"testName"},[e._v("Download")]),r("div",{staticClass:"picArea"},[r("canvas",{staticClass:"meter",attrs:{id:"dlMeter"}}),r("div",{staticClass:"meterText",attrs:{id:"dlText"}},[e._v(e._s(e.dlText)+" Mbps")])])]),r("el-col",{staticClass:"testArea"},[r("div",{staticClass:"testName"},[e._v("Upload")]),r("div",{staticClass:"picArea"},[r("canvas",{staticClass:"meter",attrs:{id:"ulMeter"}}),r("div",{staticClass:"meterText",attrs:{id:"ulText"}},[e._v(e._s(e.ulText)+" Mbps")])])])],1),r("el-row",{attrs:{type:"flex",justify:"space-around"}},[r("el-col",{attrs:{id:"ipArea"}},[r("div",{attrs:{id:"ipAndIsp"}},[e._v(e._s(e.ipAndIspContent))]),r("div",{attrs:{id:"ipPositionAndAccessMethod"}},[e._v(" "+e._s(e.ipPositionAndAccessMethodContent)+" ")])])],1)],1)},y=[],w=(r("a9e3"),r("b680"),r("b0c0"),r("ac1f"),r("466d"),r("ecd6")),S=r.n(w);function x(){return S()('(function(e){var t={};function r(n){if(t[n])return t[n].exports;var o=t[n]={i:n,l:!1,exports:{}};return e[n].call(o.exports,o,o.exports,r),o.l=!0,o.exports}r.m=e,r.c=t,r.d=function(e,t,n){r.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:n})},r.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},r.t=function(e,t){if(1&t&&(e=r(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var n=Object.create(null);if(r.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var o in e)r.d(n,o,function(t){return e[t]}.bind(null,o));return n},r.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return r.d(t,"a",t),t},r.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},r.p="/",r(r.s="87fd")})({"87fd":function(e,t){var r=-1,n="",o="",i="",a="",l="",s=0,u=0,d=0,c=null,p="",f="",m="",g="";function _(e){y.telemetry_level>=2&&(g+=Date.now()+": "+e+"\\n")}function h(e){y.telemetry_level>=3&&(g+=Date.now()+": "+e+"\\n")}function v(e){y.telemetry_level>=2&&(g+=Date.now()+" WARN: "+e+"\\n"),console.warn(e)}var y={mpot:!1,test_order:"IP_D_U",time_ul_max:35,time_dl_max:35,time_auto:!0,time_ulGraceTime:3,time_dlGraceTime:1.5,count_ping:10,url_dl:"/speed/api/download",url_ul:"/speed/api/empty",url_ping:"/speed/api/empty",url_getIp:"/speed/api/ip",getIp_ispInfo:!0,getIp_ispInfo_distance:"km",xhr_dlMultistream:6,xhr_ulMultistream:3,xhr_multistreamDelay:300,xhr_ignoreErrors:1,xhr_dlUseBlob:!1,xhr_ul_blob_megabytes:20,garbagePhp_chunkSize:100,enable_quirks:!0,ping_allowPerformanceApi:!0,overheadCompensationFactor:1.06,useMebibits:!1,telemetry_level:0,url_telemetry:"/api/history",telemetry_extra:"",server_id:null},b=null,w=null,x=0;function T(e){return e.match(/\\?/)?"&":"?"}function M(){if(h("stopping pending XHRs"),b){for(var e=0;e<b.length;e++){try{b[e].onprogress=null,b[e].onload=null,b[e].onerror=null}catch(t){console.error(t)}try{b[e].upload.onprogress=null,b[e].upload.onload=null,b[e].upload.onerror=null}catch(t){console.error(t)}try{b[e].abort()}catch(t){console.error(t)}try{delete b[e]}catch(t){console.error(t)}}b=null}}self.addEventListener("message",(function(e){var t=e.data.split(" ");if("status"===t[0]&&postMessage(JSON.stringify({testState:r,dlStatus:n,ulStatus:o,pingStatus:i,clientIp:l,isp:m,ipPosition:p,ipAccessMethod:f,jitterStatus:a,dlProgress:s,ulProgress:u,pingProgress:d,testId:c})),"start"===t[0]&&-1===r){r=0;try{var g={};try{var b=e.data.substring(5);b&&(g=JSON.parse(b))}catch(e){v("Error parsing custom settings JSON. Please check your syntax")}for(var T in g)"undefined"!==typeof y[T]?y[T]=g[T]:v("Unknown setting ignored: "+T);var P=navigator.userAgent;(y.enable_quirks||"undefined"!==typeof g.enable_quirks&&g.enable_quirks)&&(/Firefox.(\\d+\\.\\d+)/i.test(P)&&"undefined"===typeof g.ping_allowPerformanceApi&&(y.ping_allowPerformanceApi=!1),/Edge.(\\d+\\.\\d+)/i.test(P)&&"undefined"===typeof g.xhr_dlMultistream&&(y.xhr_dlMultistream=3),/Chrome.(\\d+)/i.test(P)&&self.fetch&&"undefined"===typeof g.xhr_dlMultistream&&(y.xhr_dlMultistream=5)),/Edge.(\\d+\\.\\d+)/i.test(P)&&(y.forceIE11Workaround=!0),/PlayStation 4.(\\d+\\.\\d+)/i.test(P)&&(y.forceIE11Workaround=!0),/Chrome.(\\d+)/i.test(P)&&/Android|iPhone|iPad|iPod|Windows Phone/i.test(P)&&(y.xhr_ul_blob_megabytes=4),/^((?!chrome|android|crios|fxios).)*safari/i.test(P)&&(y.forceIE11Workaround=!0),"undefined"!==typeof g.telemetry_level&&(y.telemetry_level="basic"===g.telemetry_level?1:"full"===g.telemetry_level?2:"debug"===g.telemetry_level?3:0),y.test_order=y.test_order.toUpperCase()}catch(e){v("Possible error in custom test settings. Some settings might not have been applied. Exception: "+e)}h(JSON.stringify(y)),x=0;var I=!1,S=!1,E=!1,A=!1,N=function(){if(5!=r)if(x>=y.test_order.length)y.telemetry_level>0?q((function(e){r=4,null!=e&&(c=e)})):r=4;else switch(y.test_order.charAt(x)){case"I":if(x++,I)return void N();I=!0,k(N);break;case"D":if(x++,S)return void N();S=!0,r=1,D(N);break;case"U":if(x++,E)return void N();E=!0,r=3,O(N);break;case"P":if(x++,A)return void N();A=!0,r=2,F(N);break;case"_":x++,setTimeout(N,1e3);break;default:x++}};N()}if("abort"===t[0]){if(r>=4)return;_("manually aborted"),M(),N=null,w&&clearInterval(w),y.telemetry_level>1&&q((function(){})),r=5,n="",o="",i="",a="",l="",m="",p="",f="",s=0,u=0,d=0}}));var P=!1,I="";function k(e){if(h("getIp"),!P){P=!0;var t=(new Date).getTime();b=new XMLHttpRequest,b.onload=function(){_("IP: "+b.responseText+", took "+((new Date).getTime()-t)+"ms");try{var r=JSON.parse(b.responseText).data;l=r.ip,null!=l&&void 0!=l||(l="Unknown"),m=r.isp,null!=m&&void 0!=m||(m="Unknown"),p=r.position,null!=p&&void 0!=p||(p="Unknown"),f=r.accessMethod,null!=f&&void 0!=p||(f="Unknown")}catch(n){l="Failed to get IP",I=""}e()},b.onerror=function(){_("getIp failed, took "+((new Date).getTime()-t)+"ms"),e()},b.open("GET",y.url_getIp+T(y.url_getIp)+(y.mpot?"cors=true&":"")+(y.getIp_ispInfo?"isp=true"+(y.getIp_ispInfo_distance?"&distance="+y.getIp_ispInfo_distance+"&":"&"):"&")+"r="+Math.random(),!0),b.send()}}var S=!1;function D(e){if(h("dlTest"),!S){S=!0;var t=0,o=(new Date).getTime(),i=0,a=!1,l=!1;b=[];for(var u=function(e,n){setTimeout(function(){if(1===r){h("dl test stream started "+e+" "+n);var o=0,i=new XMLHttpRequest;b[e]=i,b[e].onprogress=function(n){if(h("dl stream progress event "+e+" "+n.loaded),1!==r)try{i.abort()}catch(l){console.error(l)}var a=n.loaded<=0?0:n.loaded-o;isNaN(a)||!isFinite(a)||a<0||(t+=a,o=n.loaded)}.bind(this),b[e].onload=function(){h("dl stream finished "+e);try{b[e].abort()}catch(t){console.error(t)}u(e,0)}.bind(this),b[e].onerror=function(){h("dl stream failed "+e),0===y.xhr_ignoreErrors&&(l=!0);try{b[e].abort()}catch(t){console.error(t)}delete b[e],1===y.xhr_ignoreErrors&&u(e,0)}.bind(this);try{y.xhr_dlUseBlob?b[e].responseType="blob":b[e].responseType="arraybuffer"}catch(a){console.error(a)}b[e].open("GET",y.url_dl+T(y.url_dl)+(y.mpot?"cors=true&":"")+"r="+Math.random()+"&ckSize="+y.garbagePhp_chunkSize,!0),b[e].send()}}.bind(this),1+n)}.bind(this),d=0;d<y.xhr_dlMultistream;d++)u(d,y.xhr_multistreamDelay*d);w=setInterval(function(){h("DL: "+n+(a?"":" (in grace time)"));var r=(new Date).getTime()-o;if(a&&(s=(r+i)/(1e3*y.time_dl_max)),!(r<200))if(a){var u=t/(r/1e3);if(y.time_auto){var d=6.4*u/1e5;i+=d>800?800:d}n=(8*u*y.overheadCompensationFactor/(y.useMebibits?1048576:1e6)).toFixed(2),((r+i)/1e3>y.time_dl_max||l)&&((l||isNaN(n))&&(n="Fail"),M(),clearInterval(w),s=1,_("dlTest: "+n+", took "+((new Date).getTime()-o)+"ms"),e())}else r>1e3*y.time_dlGraceTime&&(t>0&&(o=(new Date).getTime(),i=0,t=0),a=!0)}.bind(this),200)}}var E=!1;function O(e){if(h("ulTest"),!E){E=!0;var t=new ArrayBuffer(1048576),n=Math.pow(2,32)-1;try{t=new Uint32Array(t);for(var i=0;i<t.length;i++)t[i]=Math.random()*n}catch(d){console.error(d)}var a=[],l=[];for(let e=0;e<y.xhr_ul_blob_megabytes;e++)a.push(t);a=new Blob(a),t=new ArrayBuffer(262144);try{t=new Uint32Array(t);for(let e=0;e<t.length;e++)t[e]=Math.random()*n}catch(d){console.error(d)}l.push(t),l=new Blob(l);var s=function(){var t=0,n=(new Date).getTime(),i=0,s=!1,c=!1;b=[];for(var p=function(e,n){setTimeout(function(){if(3===r){h("ul test stream started "+e+" "+n);var o,i=0,s=new XMLHttpRequest;if(b[e]=s,y.forceIE11Workaround)o=!0;else try{b[e].upload.onprogress,o=!1}catch(d){o=!0}if(o){b[e].onload=b[e].onerror=function(){h("ul stream progress event (ie11wa)"),t+=l.size,p(e,0)},b[e].open("POST",y.url_ul+T(y.url_ul)+(y.mpot?"cors=true&":"")+"r="+Math.random(),!0);try{b[e].setRequestHeader("Content-Encoding","identity")}catch(d){console.error(d)}b[e].send(l)}else{b[e].upload.onprogress=function(n){if(h("ul stream progress event "+e+" "+n.loaded),3!==r)try{s.abort()}catch(d){console.error(d)}var o=n.loaded<=0?0:n.loaded-i;isNaN(o)||!isFinite(o)||o<0||(t+=o,i=n.loaded)}.bind(this),b[e].upload.onload=function(){h("ul stream finished "+e),p(e,0)}.bind(this),b[e].upload.onerror=function(){h("ul stream failed "+e),0===y.xhr_ignoreErrors&&(c=!0);try{b[e].abort()}catch(d){console.error(d)}delete b[e],1===y.xhr_ignoreErrors&&p(e,0)}.bind(this),b[e].open("POST",y.url_ul+T(y.url_ul)+(y.mpot?"cors=true&":"")+"r="+Math.random(),!0);try{b[e].setRequestHeader("Content-Encoding","identity")}catch(d){console.error(d)}b[e].send(a)}}}.bind(this),n)}.bind(this),f=0;f<y.xhr_ulMultistream;f++)p(f,y.xhr_multistreamDelay*f);w=setInterval(function(){h("UL: "+o+(s?"":" (in grace time)"));var r=(new Date).getTime()-n;if(s&&(u=(r+i)/(1e3*y.time_ul_max)),!(r<200))if(s){var a=t/(r/1e3);if(y.time_auto){var l=6.4*a/1e5;i+=l>800?800:l}o=(8*a*y.overheadCompensationFactor/(y.useMebibits?1048576:1e6)).toFixed(2),((r+i)/1e3>y.time_ul_max||c)&&((c||isNaN(o))&&(o="Fail"),M(),clearInterval(w),u=1,_("ulTest: "+o+", took "+((new Date).getTime()-n)+"ms"),e())}else r>1e3*y.time_ulGraceTime&&(t>0&&(n=(new Date).getTime(),i=0,t=0),s=!0)}.bind(this),200)}.bind(this);y.mpot?(h("Sending POST request before performing upload test"),b=[],b[0]=new XMLHttpRequest,b[0].onload=b[0].onerror=function(){h("POST request sent, starting upload test"),s()}.bind(this),b[0].open("POST",y.url_ul+"?cors=true"),b[0].send()):s()}}var A=!1;function F(e){if(h("pingTest"),!A){A=!0;var t=(new Date).getTime(),r=null,n=0,o=0,l=0,s=0;b=[];var u=function(){h("ping"),d=l/y.count_ping,r=(new Date).getTime(),b[0]=new XMLHttpRequest,b[0].onload=function(){if(h("pong"),0===l)r=(new Date).getTime();else{var c=(new Date).getTime()-r;if(y.ping_allowPerformanceApi)try{var p=performance.getEntries();p=p[p.length-1];var f=p.responseStart-p.requestStart;f<=0&&(f=p.duration),f>0&&f<c&&(c=f)}catch(g){h("Performance API not supported, using estimate")}c<1&&(c=s),c<1&&(c=1);var m=Math.abs(c-s);1===l?n=c:(c<n&&(n=c),o=2===l?m:.9365*o+.0625*m),s=c}i=n.toFixed(2),a=o.toFixed(2),l++,h("ping: "+i+" jitter: "+a),l<y.count_ping?u():(d=1,_("ping: "+i+" jitter: "+a+", took "+((new Date).getTime()-t)+"ms"),e())}.bind(this),b[0].onerror=function(){h("ping failed"),0===y.xhr_ignoreErrors&&(i="Fail",a="Fail",M(),_("ping test failed, took "+((new Date).getTime()-t)+"ms"),d=1,e()),1===y.xhr_ignoreErrors&&u(),2===y.xhr_ignoreErrors&&(l++,l<y.count_ping?u():(d=1,_("ping: "+i+" jitter: "+a+", took "+((new Date).getTime()-t)+"ms"),e()))}.bind(this),b[0].open("GET",y.url_ping+T(y.url_ping)+(y.mpot?"cors=true&":"")+"r="+Math.random(),!0),b[0].send()}.bind(this);u()}}function q(e){if(!(y.telemetry_level<1)){b=new XMLHttpRequest,b.onload=function(){try{var t=b.responseText.split(" ");if("id"==t[0])try{var r=t[1];e(r)}catch(n){e(null)}else e(null)}catch(n){e(null)}},b.onerror=function(){console.log("TELEMETRY ERROR "+b.status),e(null)},b.open("POST",y.url_telemetry+T(y.url_telemetry)+(y.mpot?"cors=true&":"")+"r="+Math.random(),!0);try{var t=new FormData;t.append("ip",l),t.append("dl",n),t.append("ul",o),t.append("ping",i),t.append("jitter",a),t.append("ua",navigator.userAgent),t.append("testPointId",y.server_id),t.append("extraAttribute",JSON.stringify({isp:m,position:p,accessMethod:f})),b.send(t),console.log(g),console.log(I)}catch(r){console.error(r)}}}}});\n//# sourceMappingURL=speedtest.worker.b84f1fac.worker.js.map',"Worker",void 0,r.p+"static/js/speedtest.worker.b84f1fac.worker.js")}var T=function(){this._serverList=[],this._selectedServer=null,this._settings={},this._state=0,console.log("LibreSpeed by Federico Dossena v5.2 - https://github.com/librespeed/speedtest")};T.prototype={constructor:T,getState:function(){return this._state},setParameter:function(e,t){if(0!=this._state)throw"You cannot change the test settings after adding server or starting the test";this._settings[e]=t,"temeletry_extra"===e&&(this._originalExtra=this._settings.telemetry_extra)},_checkServerDefinition:function(e){try{if("string"!==typeof e.name)throw"Name string missing from server definition (name)";if("string"!==typeof e.server)throw"Server address string missing from server definition (server)";if("/"==e.server.charAt(e.server.length-1)&&(e.server=e.server.substr(0,e.server.length-1)),!/^(http:\/\/|https:\/\/).+$/.test(e.server))throw"Server address string must start with http:// or https://";if("string"!==typeof e.dlURL)throw"Download URL string missing from server definition (dlURL)";if("/"!=e.dlURL.charAt(0)&&(e.dlURL="/"+e.dlURL),"string"!==typeof e.ulURL)throw"Upload URL string missing from server definition (ulURL)";if("/"!=e.ulURL.charAt(0)&&(e.ulURL="/"+e.ulURL),"string"!==typeof e.pingURL)throw"Ping URL string missing from server definition (pingURL)";if("/"!=e.pingURL.charAt(0)&&(e.pingURL="/"+e.pingURL),"string"!==typeof e.getIpURL)throw"GetIP URL string missing from server definition (getIpURL)";"/"!=e.getIpURL.charAt(0)&&(e.getIpURL="/"+e.getIpURL)}catch(t){throw console.error(t),"Invalid server definition"}},addTestPoint:function(e){if(this._checkServerDefinition(e),0==this._state&&(this._state=1),1!=this._state)throw"You can't add a server after server selection";this._settings.mpot=!0,this._serverList.push(e)},addTestPoints:function(e){for(var t=0;t<e.length;t++)this.addTestPoint(e[t])},loadServerList:function(e,t){if(0==this._state&&(this._state=1),1!=this._state)throw"You can't add a server after server selection";this._settings.mpot=!0;var r=new XMLHttpRequest;r.onload=function(){try{var e=JSON.parse(r.responseText);if(!e.status)return void t(null);e=e.data;for(var n=0;n<e.length;n++)e[n].dlURL="/speed/api/download",e[n].ulURL="/speed/api/empty",e[n].pingURL="/speed/api/empty",e[n].getIpURL="/speed/api/ip",this._checkServerDefinition(e[n]);this.addTestPoints(e),t(e)}catch(i){console.error(i),t(null)}}.bind(this),r.onerror=function(){t(null)},r.open("GET",e),r.send()},getSelectedServer:function(){if(this._state<2||null==this._selectedServer)throw"No server is selected";return this._selectedServer},setSelectedServer:function(e){if(this._checkServerDefinition(e),3==this._state)throw"You can't select a server while the test is running";this._selectedServer=e,this._state=2},selectServer:function(e){if(1!=this._state){if(0==this._state)throw"No test points added";if(2==this._state)throw"Server already selected";if(this._state>=3)throw"You can't select a server while the test is running"}if(this._selectServerCalled)throw"selectServer already called";this._selectServerCalled=!0;for(var t=function(e,t){var r=2e3,n=!0;/MSIE.(\d+\.\d+)/i.test(navigator.userAgent)&&(n=!1);var i=function(e,t){e+=(e.match(/\?/)?"&":"?")+"cors=true";var i=new XMLHttpRequest,s=(new Date).getTime();if(i.onload=function(){if(0==i.responseText.length){var r=(new Date).getTime()-s;try{var n=performance.getEntriesByName(e);n=n[n.length-1];var a=n.responseStart-n.requestStart;a<=0&&(a=n.duration),a>0&&a<r&&(r=a)}catch(o){console.error(o)}t(r)}else t(-1)}.bind(this),i.onerror=function(){t(-1)}.bind(this),i.open("GET",e),n)try{i.timeout=r,i.ontimeout=i.onerror}catch(a){console.error(a)}i.send()}.bind(this),s=3,a=500,o=function(e,t){var r=0;if(e.pingT=-1,-1==e.server.indexOf(location.protocol))t();else{var n=function(){r++!=s?i(e.server+e.pingURL,function(r){r>=0?((r<e.pingT||-1==e.pingT)&&(e.pingT=r),r<a?n():t()):t()}.bind(this)):t()}.bind(this);n()}}.bind(this),l=0,u=function(){for(var r=null,n=0;n<e.length;n++)-1!=e[n].pingT&&(null==r||e[n].pingT<r.pingT)&&(r=e[n]);t(r)}.bind(this),d=function(){l!=e.length?o(e[l++],d):u()}.bind(this);d()}.bind(this),r=6,n=[],i=0;i<r;i++)n[i]=[];for(var s=0;s<this._serverList.length;s++)n[s%r].push(this._serverList[s]);for(var a=0,o=null,l=0;l<r;l++)t(n[l],function(t){null!=t&&(null==o||t.pingT<o.pingT)&&(o=t),a++,a==r&&(this._selectedServer=o,this._state=2,e&&e(o))}.bind(this))},start:function(){if(3==this._state)throw"Test already running";if(this.worker=new x,this.worker.onmessage=function(e){if(e.data!==this._prevData){this._prevData=e.data;var t=JSON.parse(e.data);try{this.onupdate&&this.onupdate(t)}catch(e){console.error("Speedtest onupdate event threw exception: "+e)}if(t.testState>=4){try{this.onend&&this.onend(5==t.testState)}catch(e){console.error("Speedtest onend event threw exception: "+e)}clearInterval(this.updater),this._state=4}}}.bind(this),this.updater=setInterval(function(){this.worker.postMessage("status")}.bind(this),200),1==this._state)throw"When using multiple points of test, you must call selectServer before starting the test";2==this._state&&(this._settings.url_dl=this._selectedServer.server+":"+this._selectedServer.port+this._selectedServer.dlURL,this._settings.url_ul=this._selectedServer.server+":"+this._selectedServer.port+this._selectedServer.ulURL,this._settings.url_ping=this._selectedServer.server+":"+this._selectedServer.port+this._selectedServer.pingURL,this._settings.url_getIp=this._selectedServer.server+":"+this._selectedServer.port+this._selectedServer.getIpURL,this._settings.url_telemetry=window.location.protocol+"//"+window.location.host+"/api/history",this._settings.server_id=this._selectedServer.id,"undefined"!==typeof this._originalExtra?this._settings.telemetry_extra=JSON.stringify({server:this._selectedServer.name,extra:this._originalExtra}):this._settings.telemetry_extra=JSON.stringify({server:this._selectedServer.name})),this._state=3,this.worker.postMessage("start "+JSON.stringify(this._settings))},abort:function(){if(this._state<3)throw"You cannot abort a test that's not started yet";this._state<4&&this.worker.postMessage("abort")}};var P=T,I={name:"Speedtest",data:function(){return{s:new P,pointReady:!1,uiData:null,startButtonType:"primary",startButtonForbidden:!0,startButtonStart:!1,ipAndIspContent:"",ipPositionAndAccessMethodContent:"",dlText:0,ulText:0,pingText:0,jitterText:0,serverLists:[],changeServerId:1}},methods:{startStop:function(){var e=this;this.pointReady&&(3==this.s.getState()?(this.s.abort(),this.startButtonType="primary",this.startButtonStart=!1,this.initUI()):(this.startButtonType="danger",this.startButtonStart=!0,this.s.onupdate=function(t){e.uiData=t},this.s.onend=function(){e.startButtonType="primary",e.startButtonStart=!1,e.updateUI(!0)},this.s.start()))},initUI:function(){var e=/Trident.*rv:(\d+\.\d+)/i.test(navigator.userAgent)?"#EAEAEA":"#80808040",t="#6060AA",r="#616161";this.drawMeter(this.I("dlMeter"),0,e,t,0),this.drawMeter(this.I("ulMeter"),0,e,r,0)},updateUI:function(e){var t=/Trident.*rv:(\d+\.\d+)/i.test(navigator.userAgent)?"#EAEAEA":"#80808040",r="#6060AA",n="#616161",i=t;if((e||3==this.s.getState())&&null!=this.uiData){var s=this.uiData.testState,a="";this.uiData.clientIp.length&&this.uiData.isp.length&&(a="-"),this.ipAndIspContent=this.uiData.clientIp+a+this.uiData.isp,a=this.uiData.ipPosition.length&&this.uiData.ipAccessMethod.length?"-":"",this.ipPositionAndAccessMethodContent=this.uiData.ipPosition+a+this.uiData.ipAccessMethod,this.dlText=1==s&&0==this.uiData.dlStatus?"...":this.format(this.uiData.dlStatus),this.drawMeter(this.I("dlMeter"),this.mbpsToAmount(Number(this.uiData.dlStatus*(1==s?this.oscillate():1))),t,r,Number(this.uiData.dlProgress),i),this.ulText=3==s&&0==this.uiData.ulStatus?"...":this.format(this.uiData.ulStatus),this.drawMeter(this.I("ulMeter"),this.mbpsToAmount(Number(this.uiData.ulStatus*(3==s?this.oscillate():1))),t,n,Number(this.uiData.ulProgress),i),this.pingText=this.format(this.uiData.pingStatus),this.jitterText=this.format(this.uiData.jitterStatus)}},I:function(e){return document.getElementById(e)},mbpsToAmount:function(e){return 1-1/Math.pow(1.3,Math.sqrt(e))},format:function(e){return e=Number(e),e<10?e.toFixed(2):e<100?e.toFixed(1):e.toFixed(0)},oscillate:function(){return 1+.02*Math.sin(Date.now()/100)},drawMeter:function(e,t,r,n,i,s){var a=e.getContext("2d"),o=window.devicePixelRatio||1,l=e.clientWidth*o,u=e.clientHeight*o,d=.0055*u;e.width==l&&e.height==u?a.clearRect(0,0,l,u):(e.width=l,e.height=u),a.beginPath(),a.strokeStyle=r,a.lineWidth=12*d,a.arc(e.width/2,e.height-58*d,e.height/1.8-a.lineWidth,1.1*-Math.PI,.1*Math.PI),a.stroke(),a.beginPath(),a.strokeStyle=n,a.lineWidth=12*d,a.arc(e.width/2,e.height-58*d,e.height/1.8-a.lineWidth,1.1*-Math.PI,t*Math.PI*1.2-1.1*Math.PI),a.stroke(),"undefined"!==typeof i&&(a.fillStyle=s,a.fillRect(.3*e.width,e.height-16*d,.4*e.width*i,4*d))},frame:function(){requestAnimationFrame(this.frame),this.updateUI()},changeServer:function(e){e--,this.s.setSelectedServer(this.s._serverList[e])}},mounted:function(){var e=this;this.s.setParameter("telemetry_level","basic"),this.initUI(),this.frame(),this.s.loadServerList(u["a"]+"/api/testpoint?cors=1",(function(t){if(null==t)return e.changeServerId="测速节点加载失败",void alert("测速节点列表加载失败");e.changeServerId=t[0].id,e.startButtonForbidden=!1,e.s.setSelectedServer(t[0]),e.serverLists=t,e.pointReady=!0,e.startButtonType="primary"}))}},M=I,A=(r("6d9f"),Object(p["a"])(M,b,y,!1,null,"779a9b9e",null)),D=A.exports,L={name:"Index",components:{Speedtest:D}},E=L,k=(r("8229"),Object(p["a"])(E,v,_,!1,null,"377ca1bc",null)),R=k.exports,U=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",[r("el-row",[r("el-col",[r("el-table",{attrs:{data:e.historyData,border:""}},[r("el-table-column",{attrs:{prop:"id",label:"Id"}}),r("el-table-column",{attrs:{prop:"time",label:"时间"}}),r("el-table-column",{attrs:{prop:"ip",label:"ip"}}),r("el-table-column",{attrs:{prop:"extraAttribute.position",label:"接入地点"}}),r("el-table-column",{attrs:{prop:"extraAttribute.accessMethod",label:"接入方式"}}),r("el-table-column",{attrs:{prop:"dl",label:"下载速度/Mbps"}}),r("el-table-column",{attrs:{prop:"ul",label:"上传速度/Mbps"}}),r("el-table-column",{attrs:{prop:"ping",label:"ping/ms"}}),r("el-table-column",{attrs:{prop:"jitter",label:"jitter/ms"}}),r("el-table-column",{attrs:{prop:"testPointName",label:"测速节点名称"}})],1)],1)],1),r("el-row",{staticClass:"page-row"},[r("el-col",{attrs:{span:24}},[r("el-pagination",{staticClass:"page",attrs:{background:"",layout:"total, sizes, prev, pager, next, jumper","page-sizes":[5,10,25,50],total:e.historyDataCount,"page-size":e.pageSize},on:{"current-change":e.pageChange,"size-change":e.sizeChange}})],1)],1)],1)},C=[],N={name:"history",data:function(){return{historyData:[],historyDataCount:20,pageSize:10,currentPage:1}},methods:{pageChange:function(e){var t=this;this.currentPage=e,l.a.get(u["a"]+"/api/history/user",{params:{index:e,size:this.pageSize}}).then((function(e){var r=e.data;r.status?(t.historyDataCount=r.data.count,t.historyData=r.data.list):t.$message.error(r.data.message)})).catch((function(e){console.error(e),t.$message.error("历史数据加载失败")}))},sizeChange:function(e){this.pageSize=e,this.pageChange(this.currentPage)}},mounted:function(){this.pageChange(1)}},O=N,j=(r("6589"),Object(p["a"])(O,U,C,!1,null,"b2140966",null)),B=j.exports;n["default"].config.productionTip=!1,n["default"].use(m.a),n["default"].use(i["a"]);var F=[{path:"/",component:R},{path:"/history",component:B}],q=new i["a"]({routes:F});new n["default"]({render:function(e){return e(f)},router:q}).$mount("#app")},df64:function(e,t,r){"use strict";r("d672")},f426:function(e,t,r){}});
//# sourceMappingURL=index.626ce366.js.map