/*********************************************************************
 *  #### Twitter Post Fetcher v17.0.0 ####
 *  Coded by Jason Mayes 2015. A present to all the developers out there.
 *  www.jasonmayes.com
 *  Please keep this disclaimer with my code if you use it. Thanks. :-)
 *  Got feedback or questions, ask here:
 *  http://www.jasonmayes.com/projects/twitterApi/
 *  Github: https://github.com/jasonmayes/Twitter-Post-Fetcher
 *  Updates will be posted to this site.
 *********************************************************************/

$(".conteiner a").on('click',  function(){
(function(root, factory) {
    if (typeof define === 'function' && define.amd) {
        define([], factory);
    } else if (typeof exports === 'object') {
        module.exports = factory();
    } else {
        factory();
    }
}(this, function() {
    var domNode = '';
    var maxTweets = 20;
    var parseLinks = true;
    var queue = [];
    var inProgress = false;
    var printTime = true;
    var printUser = true;
    var formatterFunction = null;
    var supportsClassName = true;
    var showRts = true;
    var customCallbackFunction = null;
    var showInteractionLinks = true;
    var showImages = false;
    var targetBlank = true;
    var lang = 'en';
    var permalinks = true;
    var dataOnly = false;
    var script = null;
    var scriptAdded = false;

    function handleTweets(tweets) {
        if (customCallbackFunction === null) {
            var x = tweets.length;
            var n = 0;
            var element = document.getElementById(domNode);
            var html = '<ul>';
            while (n < x) {
                html += '<li>' + tweets[n] + '</li>';
                n++;
            }
            html += '</ul>';
            element.innerHTML = html;
        } else {
            customCallbackFunction(tweets);
        }
    }

    function strip(data) {
        return data.replace(/<b[^>]*>(.*?)<\/b>/gi, function(a, s) {
            return s;
        }).replace(/class="(?!(tco-hidden|tco-display|tco-ellipsis))+.*?"|data-query-source=".*?"|dir=".*?"|rel=".*?"/gi, '');
    }

    function targetLinksToNewWindow(el) {
        var links = el.getElementsByTagName('a');
        for (var i = links.length - 1; i >= 0; i--) {
            links[i].setAttribute('target', '_blank');
        }
    }


    var twitterFetcher = {
        fetch: function(config) {



            if (config.customCallback === undefined) {
                config.customCallback = null;
            } else {

                maxTweets = config.maxTweets;
                printUser = config.showUser;

                formatterFunction = config.dateFunction;
                customCallbackFunction = config.customCallback;
                dataOnly = config.dataOnly;
                var head = document.getElementsByTagName('head')[0];
                if (script !== null) {
                    head.removeChild(script);
                }
                script = document.createElement('script');
                script.type = 'text/javascript';
                if (config.list !== undefined) {
                    script.src = 'https://syndication.twitter.com/timeline/list?' + 'callback=__twttrf.callback&dnt=false&list_slug=' +
                        config.list.listSlug + '&screen_name=' + config.list.screenName + '&suppress_response_codes=true&lang=' + (config.lang || lang) + '&rnd=' + Math.random();
                } else {
                    script.src = 'https://cdn.syndication.twimg.com/widgets/timelines/' +
                        config.id + '?&lang=' + (config.lang || lang) + '&callback=__twttrf.callback&' + 'suppress_response_codes=true&rnd=' + Math.random();
                }
                head.appendChild(script);
            }
        },
        callback: function(data) {
            if (data === undefined || data.body === undefined) {
                inProgress = false;
                if (queue.length > 0) {
                    twitterFetcher.fetch(queue[0]);
                    queue.splice(0, 1);
                }
                return;
            }

            var div = document.createElement('div');
            div.innerHTML = data.body;
            if (typeof(div.getElementsByClassName) === 'undefined') {
                supportsClassName = false;
            }

            function swapDataSrc(element) {
                /*
                 IF U WANT SEE AVATAR image 

                 var avatarImg = element.getElementsByTagName('img')[0];
                 avatarImg.src = avatarImg.getAttribute('data-src-2x'); */
                return element;
            }
            var tweets = [];
            var authors = [];
            var times = [];
            var images = [];
            var rts = [];
            var tids = [];
            var permalinksURL = [];
            var x = 0;
            if (supportsClassName) {
                var tmp = div.getElementsByClassName('timeline-Tweet');
                while (x < tmp.length) {
                    if (tmp[x].getElementsByClassName('timeline-Tweet-retweetCredit').length > 0) {
                        rts.push(true);
                    } else {
                        rts.push(false);
                    }
                    if (!rts[x] || rts[x] && showRts) {
                        tweets.push(tmp[x].getElementsByClassName('timeline-Tweet-text')[0]);
                        tids.push(tmp[x].getAttribute('data-tweet-id'));
                        if (printUser) {
                            authors.push(swapDataSrc(tmp[x].getElementsByClassName('timeline-Tweet-author')[0]));
                        }
                        times.push(tmp[x].getElementsByClassName('dt-updated')[0]);
                        permalinksURL.push(tmp[x].getElementsByClassName('timeline-Tweet-timestamp')[0]);
                        if (tmp[x].getElementsByClassName('timeline-Tweet-media')[0] !== undefined) {
                            images.push(tmp[x].getElementsByClassName('timeline-Tweet-media')[0]);
                        }
                    }
                    x++;
                }
            } else {
                var tmp = getElementsByClassName(div, 'timeline-Tweet');
                while (x < tmp.length) {
                    x++;
                }
            }
            if (tweets.length > maxTweets) {
                tweets.splice(maxTweets, (tweets.length - maxTweets));


            }
            var arrayTweets = [];
            var x = tweets.length;
            var n = 0;
            if (dataOnly) {
                while (n < x) {
                    arrayTweets.push({
                        tweet: tweets[n].innerHTML

                    });
                    n++;
                }
            } else {
                while (n < x) {

                    var op = '';
                    if (parseLinks) {
                        if (targetBlank) {

                            if (printUser) {
                                targetLinksToNewWindow(authors[n]);
                            }
                        }
                        if (printUser) {
                            op += '<div class="user">' + strip(authors[n].innerHTML) + " says" + '</div>';
                        }
                        op += '<p class="tweet">' + strip(tweets[n].innerHTML) + '</p>';
                        /* SHOW HOW MANY SEC AGO 


                         if (printTime) {
                             if (permalinks) {
                                 op += '<p class="timePosted"><a href="' + permalinksURL[n] + '">' + times[n].getAttribute('aria-label') + '</a></p>';
                             } else {
                                 op += '<p class="timePosted">' +
                                     times[n].getAttribute('aria-label') + '</p>';
                             }
                         }*/
                    } else {
                        if (tweets[n].textContent) {
                            if (printUser) {
                                op += '<p class="user">' + authors[n].textContent + '</p>';
                            }
                            op += '<p class="tweet">' + tweets[n].textContent + '</p>';
                            if (printTime) {
                                op += '<p class="timePosted">' + times[n].textContent + '</p>';
                            }
                        }
                    }


                    if (showImages) {
                        arrayTweets.push(op);
                    } else if (!showImages && tweets[n].textContent.length) {
                        arrayTweets.push(op);
                    }
                    n++;
                }
            }
            handleTweets(arrayTweets);
            inProgress = false;

        }
    };
    window.__twttrf = twitterFetcher;
    window.twitterFetcher = twitterFetcher;
    return twitterFetcher;
}));



//TOKYO
if ($(this).attr('id')=='Tokyo' ){
var config1 = {
    "id": '870042441398833155',
    "maxTweets": 3,
    "enableLinks": true,
    "showUser": true,
    "showTime": true,
    "dateFunction": '',
    "customCallback": handleTweets,

};}

//PARIS
if ($(this).attr('id')=='Paris' ){
var config1 = {
    "id": '870043231488180228',
    "maxTweets": 3,
    "enableLinks": true,
    "showUser": true,
    "showTime": true,
    "dateFunction": '',
    "customCallback": handleTweets,

};}

//BERLIN
if ($(this).attr('id')=='Berlin' ){
var config1 = {
    "id": '870043341098037248',
    "maxTweets": 3,
    "enableLinks": true,
    "showUser": true,
    "showTime": true,
    "dateFunction": '',
    "customCallback": handleTweets,

};}
//LOS ANGELES
if ($(this).attr('id')=='Los Angeles' ){
var config1 = {
    "id": '870043069093220352',
    "maxTweets": 3,
    "enableLinks": true,
    "showUser": true,
    "showTime": true,
    "dateFunction": '',
    "customCallback": handleTweets,

};}

//NEW YORK
if ($(this).attr('id')=='New York' ){
var config1 = {
    "id": '870043478465687552',
    "maxTweets": 3,
    "enableLinks": true,
    "showUser": true,
    "showTime": true,
    "dateFunction": '',
    "customCallback": handleTweets,

};}

//BUENOS AIRES
if ($(this).attr('id')=='Buenos Aires' ){
var config1 = {
    "id": '870043682531102720',
    "maxTweets": 3,
    "enableLinks": true,
    "showUser": true,
    "showTime": true,
    "dateFunction": '',
    "customCallback": handleTweets,

};}
//RIO DE JANEIRO
if ($(this).attr('id')=='Rio de Janeiro' ){
var config1 = {
    "id": '870043814022574080',
    "maxTweets": 3,
    "enableLinks": true,
    "showUser": true,
    "showTime": true,
    "dateFunction": '',
    "customCallback": handleTweets,

};}
//BRAGA
if ($(this).attr('id')=='Braga' ){
var config1 = {
    "id": '870043958973534212',
    "maxTweets": 3,
    "enableLinks": true,
    "showUser": true,
    "showTime": true,
    "dateFunction": '',
    "customCallback": handleTweets,

};}






function handleTweets(tweets) {
    var x = tweets.length;
    var n = 0;
    var element = document.getElementById('example1');
    var html = '<ul>';
    while (n < x) {
        html += '<li>' + tweets[n] + '</li>';
        n++;
    }
    html += '</ul>';
    element.innerHTML = html;
}



twitterFetcher.fetch(config1);


});