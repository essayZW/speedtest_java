(function(e){var t={};function r(n){if(t[n])return t[n].exports;var o=t[n]={i:n,l:!1,exports:{}};return e[n].call(o.exports,o,o.exports,r),o.l=!0,o.exports}r.m=e,r.c=t,r.d=function(e,t,n){r.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:n})},r.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},r.t=function(e,t){if(1&t&&(e=r(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var n=Object.create(null);if(r.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var o in e)r.d(n,o,function(t){return e[t]}.bind(null,o));return n},r.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return r.d(t,"a",t),t},r.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},r.p="/",r(r.s="87fd")})({"87fd":function(e,t){var r=-1,n="",o="",i="",a="",l="",s=0,u=0,d=0,c=null,p="",f="",m="",g="";function _(e){y.telemetry_level>=2&&(g+=Date.now()+": "+e+"\n")}function h(e){y.telemetry_level>=3&&(g+=Date.now()+": "+e+"\n")}function v(e){y.telemetry_level>=2&&(g+=Date.now()+" WARN: "+e+"\n"),console.warn(e)}var y={mpot:!1,test_order:"IP_D_U",time_ul_max:15,time_dl_max:15,time_auto:!0,time_ulGraceTime:3,time_dlGraceTime:1.5,count_ping:10,url_dl:"/speed/api/download",url_ul:"/speed/api/empty",url_ping:"/speed/api/empty",url_getIp:"/speed/api/ip",getIp_ispInfo:!0,getIp_ispInfo_distance:"km",xhr_dlMultistream:6,xhr_ulMultistream:3,xhr_multistreamDelay:300,xhr_ignoreErrors:1,xhr_dlUseBlob:!1,xhr_ul_blob_megabytes:20,garbagePhp_chunkSize:100,enable_quirks:!0,ping_allowPerformanceApi:!0,overheadCompensationFactor:1.06,useMebibits:!1,telemetry_level:0,url_telemetry:"/api/history",telemetry_extra:"",server_id:null},b=null,w=null,x=0;function T(e){return e.match(/\?/)?"&":"?"}function M(){if(h("stopping pending XHRs"),b){for(var e=0;e<b.length;e++){try{b[e].onprogress=null,b[e].onload=null,b[e].onerror=null}catch(t){console.error(t)}try{b[e].upload.onprogress=null,b[e].upload.onload=null,b[e].upload.onerror=null}catch(t){console.error(t)}try{b[e].abort()}catch(t){console.error(t)}try{delete b[e]}catch(t){console.error(t)}}b=null}}this.addEventListener("message",(function(e){var t=e.data.split(" ");if("status"===t[0]&&postMessage(JSON.stringify({testState:r,dlStatus:n,ulStatus:o,pingStatus:i,clientIp:l,isp:m,ipPosition:p,ipAccessMethod:f,jitterStatus:a,dlProgress:s,ulProgress:u,pingProgress:d,testId:c})),"start"===t[0]&&-1===r){r=0;try{var g={};try{var b=e.data.substring(5);b&&(g=JSON.parse(b))}catch(e){v("Error parsing custom settings JSON. Please check your syntax")}for(var T in g)"undefined"!==typeof y[T]?y[T]=g[T]:v("Unknown setting ignored: "+T);var P=navigator.userAgent;(y.enable_quirks||"undefined"!==typeof g.enable_quirks&&g.enable_quirks)&&(/Firefox.(\d+\.\d+)/i.test(P)&&"undefined"===typeof g.ping_allowPerformanceApi&&(y.ping_allowPerformanceApi=!1),/Edge.(\d+\.\d+)/i.test(P)&&"undefined"===typeof g.xhr_dlMultistream&&(y.xhr_dlMultistream=3),/Chrome.(\d+)/i.test(P)&&self.fetch&&"undefined"===typeof g.xhr_dlMultistream&&(y.xhr_dlMultistream=5)),/Edge.(\d+\.\d+)/i.test(P)&&(y.forceIE11Workaround=!0),/PlayStation 4.(\d+\.\d+)/i.test(P)&&(y.forceIE11Workaround=!0),/Chrome.(\d+)/i.test(P)&&/Android|iPhone|iPad|iPod|Windows Phone/i.test(P)&&(y.xhr_ul_blob_megabytes=4),/^((?!chrome|android|crios|fxios).)*safari/i.test(P)&&(y.forceIE11Workaround=!0),"undefined"!==typeof g.telemetry_level&&(y.telemetry_level="basic"===g.telemetry_level?1:"full"===g.telemetry_level?2:"debug"===g.telemetry_level?3:0),y.test_order=y.test_order.toUpperCase()}catch(e){v("Possible error in custom test settings. Some settings might not have been applied. Exception: "+e)}h(JSON.stringify(y)),x=0;var I=!1,S=!1,E=!1,A=!1,N=function(){if(5!=r)if(x>=y.test_order.length)y.telemetry_level>0?q((function(e){r=4,null!=e&&(c=e)})):r=4;else switch(y.test_order.charAt(x)){case"I":if(x++,I)return void N();I=!0,k(N);break;case"D":if(x++,S)return void N();S=!0,r=1,D(N);break;case"U":if(x++,E)return void N();E=!0,r=3,O(N);break;case"P":if(x++,A)return void N();A=!0,r=2,F(N);break;case"_":x++,setTimeout(N,1e3);break;default:x++}};N()}if("abort"===t[0]){if(r>=4)return;_("manually aborted"),M(),N=null,w&&clearInterval(w),y.telemetry_level>1&&q((function(){})),r=5,n="",o="",i="",a="",l="",m="",p="",f="",s=0,u=0,d=0}}));var P=!1,I="";function k(e){if(h("getIp"),!P){P=!0;var t=(new Date).getTime();b=new XMLHttpRequest,b.onload=function(){_("IP: "+b.responseText+", took "+((new Date).getTime()-t)+"ms");try{var r=JSON.parse(b.responseText).data;l=r.ip,null!=l&&void 0!=l||(l="Unknown"),m=r.isp,null!=m&&void 0!=m||(m="Unknown"),p=r.position,null!=p&&void 0!=p||(p="Unknown"),f=r.accessMethod,null!=f&&void 0!=p||(f="Unknown")}catch(n){l="Failed to get IP",I=""}e()},b.onerror=function(){_("getIp failed, took "+((new Date).getTime()-t)+"ms"),e()},b.open("GET",y.url_getIp+T(y.url_getIp)+(y.mpot?"cors=true&":"")+(y.getIp_ispInfo?"isp=true"+(y.getIp_ispInfo_distance?"&distance="+y.getIp_ispInfo_distance+"&":"&"):"&")+"r="+Math.random(),!0),b.send()}}var S=!1;function D(e){if(h("dlTest"),!S){S=!0;var t=0,o=(new Date).getTime(),i=0,a=!1,l=!1;b=[];for(var u=function(e,n){setTimeout(function(){if(1===r){h("dl test stream started "+e+" "+n);var o=0,i=new XMLHttpRequest;b[e]=i,b[e].onprogress=function(n){if(h("dl stream progress event "+e+" "+n.loaded),1!==r)try{i.abort()}catch(l){console.error(l)}var a=n.loaded<=0?0:n.loaded-o;isNaN(a)||!isFinite(a)||a<0||(t+=a,o=n.loaded)}.bind(this),b[e].onload=function(){h("dl stream finished "+e);try{b[e].abort()}catch(t){console.error(t)}u(e,0)}.bind(this),b[e].onerror=function(){h("dl stream failed "+e),0===y.xhr_ignoreErrors&&(l=!0);try{b[e].abort()}catch(t){console.error(t)}delete b[e],1===y.xhr_ignoreErrors&&u(e,0)}.bind(this);try{y.xhr_dlUseBlob?b[e].responseType="blob":b[e].responseType="arraybuffer"}catch(a){console.error(a)}b[e].open("GET",y.url_dl+T(y.url_dl)+(y.mpot?"cors=true&":"")+"r="+Math.random()+"&ckSize="+y.garbagePhp_chunkSize,!0),b[e].send()}}.bind(this),1+n)}.bind(this),d=0;d<y.xhr_dlMultistream;d++)u(d,y.xhr_multistreamDelay*d);w=setInterval(function(){h("DL: "+n+(a?"":" (in grace time)"));var r=(new Date).getTime()-o;if(a&&(s=(r+i)/(1e3*y.time_dl_max)),!(r<200))if(a){var u=t/(r/1e3);if(y.time_auto){var d=6.4*u/1e5;i+=d>800?800:d}n=(8*u*y.overheadCompensationFactor/(y.useMebibits?1048576:1e6)).toFixed(2),((r+i)/1e3>y.time_dl_max||l)&&((l||isNaN(n))&&(n="Fail"),M(),clearInterval(w),s=1,_("dlTest: "+n+", took "+((new Date).getTime()-o)+"ms"),e())}else r>1e3*y.time_dlGraceTime&&(t>0&&(o=(new Date).getTime(),i=0,t=0),a=!0)}.bind(this),200)}}var E=!1;function O(e){if(h("ulTest"),!E){E=!0;var t=new ArrayBuffer(1048576),n=Math.pow(2,32)-1;try{t=new Uint32Array(t);for(var i=0;i<t.length;i++)t[i]=Math.random()*n}catch(d){console.error(d)}var a=[],l=[];for(let e=0;e<y.xhr_ul_blob_megabytes;e++)a.push(t);a=new Blob(a),t=new ArrayBuffer(262144);try{t=new Uint32Array(t);for(let e=0;e<t.length;e++)t[e]=Math.random()*n}catch(d){console.error(d)}l.push(t),l=new Blob(l);var s=function(){var t=0,n=(new Date).getTime(),i=0,s=!1,c=!1;b=[];for(var p=function(e,n){setTimeout(function(){if(3===r){h("ul test stream started "+e+" "+n);var o,i=0,s=new XMLHttpRequest;if(b[e]=s,y.forceIE11Workaround)o=!0;else try{b[e].upload.onprogress,o=!1}catch(d){o=!0}if(o){b[e].onload=b[e].onerror=function(){h("ul stream progress event (ie11wa)"),t+=l.size,p(e,0)},b[e].open("POST",y.url_ul+T(y.url_ul)+(y.mpot?"cors=true&":"")+"r="+Math.random(),!0);try{b[e].setRequestHeader("Content-Encoding","identity")}catch(d){console.error(d)}b[e].send(l)}else{b[e].upload.onprogress=function(n){if(h("ul stream progress event "+e+" "+n.loaded),3!==r)try{s.abort()}catch(d){console.error(d)}var o=n.loaded<=0?0:n.loaded-i;isNaN(o)||!isFinite(o)||o<0||(t+=o,i=n.loaded)}.bind(this),b[e].upload.onload=function(){h("ul stream finished "+e),p(e,0)}.bind(this),b[e].upload.onerror=function(){h("ul stream failed "+e),0===y.xhr_ignoreErrors&&(c=!0);try{b[e].abort()}catch(d){console.error(d)}delete b[e],1===y.xhr_ignoreErrors&&p(e,0)}.bind(this),b[e].open("POST",y.url_ul+T(y.url_ul)+(y.mpot?"cors=true&":"")+"r="+Math.random(),!0);try{b[e].setRequestHeader("Content-Encoding","identity")}catch(d){console.error(d)}b[e].send(a)}}}.bind(this),n)}.bind(this),f=0;f<y.xhr_ulMultistream;f++)p(f,y.xhr_multistreamDelay*f);w=setInterval(function(){h("UL: "+o+(s?"":" (in grace time)"));var r=(new Date).getTime()-n;if(s&&(u=(r+i)/(1e3*y.time_ul_max)),!(r<200))if(s){var a=t/(r/1e3);if(y.time_auto){var l=6.4*a/1e5;i+=l>800?800:l}o=(8*a*y.overheadCompensationFactor/(y.useMebibits?1048576:1e6)).toFixed(2),((r+i)/1e3>y.time_ul_max||c)&&((c||isNaN(o))&&(o="Fail"),M(),clearInterval(w),u=1,_("ulTest: "+o+", took "+((new Date).getTime()-n)+"ms"),e())}else r>1e3*y.time_ulGraceTime&&(t>0&&(n=(new Date).getTime(),i=0,t=0),s=!0)}.bind(this),200)}.bind(this);y.mpot?(h("Sending POST request before performing upload test"),b=[],b[0]=new XMLHttpRequest,b[0].onload=b[0].onerror=function(){h("POST request sent, starting upload test"),s()}.bind(this),b[0].open("POST",y.url_ul),b[0].send()):s()}}var A=!1;function F(e){if(h("pingTest"),!A){A=!0;var t=(new Date).getTime(),r=null,n=0,o=0,l=0,s=0;b=[];var u=function(){h("ping"),d=l/y.count_ping,r=(new Date).getTime(),b[0]=new XMLHttpRequest,b[0].onload=function(){if(h("pong"),0===l)r=(new Date).getTime();else{var c=(new Date).getTime()-r;if(y.ping_allowPerformanceApi)try{var p=performance.getEntries();p=p[p.length-1];var f=p.responseStart-p.requestStart;f<=0&&(f=p.duration),f>0&&f<c&&(c=f)}catch(g){h("Performance API not supported, using estimate")}c<1&&(c=s),c<1&&(c=1);var m=Math.abs(c-s);1===l?n=c:(c<n&&(n=c),o=2===l?m:.9365*o+.0625*m),s=c}i=n.toFixed(2),a=o.toFixed(2),l++,h("ping: "+i+" jitter: "+a),l<y.count_ping?u():(d=1,_("ping: "+i+" jitter: "+a+", took "+((new Date).getTime()-t)+"ms"),e())}.bind(this),b[0].onerror=function(){h("ping failed"),0===y.xhr_ignoreErrors&&(i="Fail",a="Fail",M(),_("ping test failed, took "+((new Date).getTime()-t)+"ms"),d=1,e()),1===y.xhr_ignoreErrors&&u(),2===y.xhr_ignoreErrors&&(l++,l<y.count_ping?u():(d=1,_("ping: "+i+" jitter: "+a+", took "+((new Date).getTime()-t)+"ms"),e()))}.bind(this),b[0].open("GET",y.url_ping+T(y.url_ping)+(y.mpot?"cors=true&":"")+"r="+Math.random(),!0),b[0].send()}.bind(this);u()}}function q(e){if(!(y.telemetry_level<1)){b=new XMLHttpRequest,b.onload=function(){try{var t=b.responseText.split(" ");if("id"==t[0])try{var r=t[1];e(r)}catch(n){e(null)}else e(null)}catch(n){e(null)}},b.onerror=function(){console.log("TELEMETRY ERROR "+b.status),e(null)},b.open("POST",y.url_telemetry+T(y.url_telemetry)+(y.mpot?"cors=true&":"")+"r="+Math.random(),!0);try{var t=new FormData;t.append("ip",l),t.append("dl",n),t.append("ul",o),t.append("ping",i),t.append("jitter",a),t.append("ua",navigator.userAgent),t.append("testPointId",y.server_id),t.append("extraAttribute",JSON.stringify({isp:m,position:p,accessMethod:f})),b.send(t),console.log(g),console.log(I)}catch(r){console.error(r)}}}}});
//# sourceMappingURL=speedtest.worker.4b5a5676.worker.js.map