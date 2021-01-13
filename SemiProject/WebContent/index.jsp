
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>html문서 제목</title>


    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />

    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>

</head>

<style>
    body,
    html {
        -webkit-font-smoothing: antialiased;
        font-family: 'Lato',
            'Helvetica Neue',
            'Helvetica',
            Helvetica,
            Arial,
            sans-serif;
        line-height: 1;

    }

    text-rendering: optimizeLegibility;

    .blue {
        color: #fff;

    }
    .slick-list {
    position: relative;
    display: block;
    overflow: hidden;
    margin: 0;
    padding: 0;
}

    .slick-slider {

        position: relative;
        display: block;
        box-sizing: border-box;
        user-select: none;
        touch-action: pan-y;
        -webkit-tap-highlight-color: transparent;
    }
    .slick-initialized .slick-slide {
    display: block;
}

    .slick-track {
    position: relative;
    top: 0;
    left: 0;
    display: block;
    margin-left: auto;
    margin-right: auto;
}

    .slick-slider {

        margin: 30px auto 50px;
    }
    .slick-slide {
    display: none;
    float: left;
    height: 100%;
    min-height: 1px;
}

    .slick-slick-dotted .slick-slider {

        margin-bottom: 30px;
    }

    element.style {
        display: block;
    }
    .slick-prev {
    left: -25px;
}
    .slick-next {
    right: -25px;
}
    .slick.dots{
        position: absolute;
        bottom: -25px;
        width: 100%;
        padding : 0;
        list-style: none;
        text-align: center;
    }
    * {
        box-sizing : border-box;
    }
    ul {
        margin-block-start: 1em;
        margin-inline-end: 1em;
        margin-inline-start: 0px;
        margin-inline-end: 0px;
        padding-inline-start: 40px;
    }
    .slick-dots li {
    position: relative;
    display: inline-block;
    width: 20px;
    height: 20px;
    margin: 0 5px;
    padding: 0;
    cursor: pointer;
}
li {
    text-align: -webkit-match-parent;
}

.slick-prev, .slick-next {
    font-size: 0;
    line-height: 0;
    position: absolute;
    top: 50%;
    display: block;
    width: 20px;
    height: 20px;
    padding: 0;
    -webkit-transform: translate(0, -50%);
    -ms-transform: translate(0, -50%);
    transform: translate(0, -50%);
    cursor: pointer;
    color: transparent;
    border: none;
    outline: none;
    background: transparent;
}
button {
    appearance: button;
    -webkit-writing-mode: horizontal-tb !important;
    text-rendering: auto;
    color: -internal-light-dark(black, white);
    letter-spacing: normal;
    word-spacing: normal;
    text-transform: none;
    text-indent: 0px;
    text-shadow: none;
    display: inline-block;
    text-align: center;
    align-items: flex-start;
    cursor: default;
    background-color: -internal-light-dark(rgb(239, 239, 239), rgb(59, 59, 59));
    box-sizing: border-box;
    margin: 0em;
    font: 400 13.3333px Arial;
    padding: 1px 6px;
    border-width: 2px;
    border-style: outset;
    border-color: -internal-light-dark(rgb(118, 118, 118), rgb(133, 133, 133));
    border-image: initial;
}

.slick-slider .slick-track, .slick-slider .slick-list {
    -webkit-transform: translate3d(0, 0, 0);
    -moz-transform: translate3d(0, 0, 0);
    -ms-transform: translate3d(0, 0, 0);
    -o-transform: translate3d(0, 0, 0);
    transform: translate3d(0, 0, 0);
}
</style>




<body>
	<jsp:include page="WEB-INF/views/common/header.jsp"></jsp:include>


    <div class="slider responsive slick-initialized slick-slider slick-dotted" role="toolbar"><button type="button"
            data-role="none" class="slick-prev slick-arrow" aria-label="Previous" role="button"
            style="display: block;">Previous</button>








        <div aria-live="polite" class="slick-list draggable">
            <div class="slick-track" style="opacity: 1; width: 2618px; transform: translate3d(-561px, 0px, 0px);"
                role="listbox">

                <button type="button" data-role="none" class="slick-prev slick-arrow slick-disabled"
                    aria-label="Previous" role="button" aria-disabled="true" style="display: block;">Previous</button>



                <div aria-live="polite" class="slick-list draggable">
                    <div class="slick-track" style="opacity: 1; width: 840px; transform: translate3d(0px, 0px, 0px);"
                        role="listbox">
                        <div class="slick-slide slick-current slick-active" style="width: 105px;" tabindex="-1"
                            role="option" aria-describedby="slick-slide50" data-slick-index="0" aria-hidden="false">
                            <h3>1</h3>
                        </div>
                        <div class="slick-slide" style="width: 105px;" tabindex="-1" role="option"
                            aria-describedby="slick-slide51" data-slick-index="1" aria-hidden="true">
                            <h3>2</h3>
                        </div>
                        <div class="slick-slide" style="width: 105px;" tabindex="-1" role="option"
                            aria-describedby="slick-slide52" data-slick-index="2" aria-hidden="true">
                            <h3>3</h3>
                        </div>
                        <div class="slick-slide" style="width: 105px;" tabindex="-1" role="option"
                            aria-describedby="slick-slide53" data-slick-index="3" aria-hidden="true">
                            <h3>4</h3>
                        </div>
                        <div class="slick-slide" style="width: 105px;" tabindex="-1" role="option"
                            aria-describedby="slick-slide54" data-slick-index="4" aria-hidden="true">
                            <h3>5</h3>
                        </div>
                        <div class="slick-slide" style="width: 105px;" tabindex="-1" role="option"
                            aria-describedby="slick-slide55" data-slick-index="5" aria-hidden="true">
                            <h3>6</h3>
                        </div>
                        <div class="slick-slide" style="width: 105px;" tabindex="-1" role="option"
                            aria-describedby="slick-slide56" data-slick-index="6" aria-hidden="true">
                            <h3>7</h3>
                        </div>
                        <div class="slick-slide" style="width: 105px;" tabindex="-1" role="option"
                            aria-describedby="slick-slide57" data-slick-index="7" aria-hidden="true">
                            <h3>8</h3>
                        </div>
                    </div>
                </div>

                <button type="button" data-role="none" class="slick-next slick-arrow" aria-label="Next" role="button"
                    style="display: block;" aria-disabled="false">Next</button>
            </div>

        </div>
        <ul class="slick-dots" style="display: block;" role="tablist">
            <li class="slick-active" aria-hidden="false" role="presentation" aria-selected="true"
                aria-controls="navigation50" id="slick-slide50"><button type="button" data-role="none" role="button"
                    tabindex="0">1</button></li>
            <li aria-hidden="true" role="presentation" aria-selected="false" aria-controls="navigation51"
                id="slick-slide51"><button type="button" data-role="none" role="button" tabindex="0">2</button></li>
            <li aria-hidden="true" role="presentation" aria-selected="false" aria-controls="navigation52"
                id="slick-slide52"><button type="button" data-role="none" role="button" tabindex="0">3</button></li>
            <li aria-hidden="true" role="presentation" aria-selected="false" aria-controls="navigation53"
                id="slick-slide53"><button type="button" data-role="none" role="button" tabindex="0">4</button></li>
            <li aria-hidden="true" role="presentation" aria-selected="false" aria-controls="navigation54"
                id="slick-slide54"><button type="button" data-role="none" role="button" tabindex="0">5</button></li>
            <li aria-hidden="true" role="presentation" aria-selected="false" aria-controls="navigation55"
                id="slick-slide55"><button type="button" data-role="none" role="button" tabindex="0">6</button></li>
            <li aria-hidden="true" role="presentation" aria-selected="false" aria-controls="navigation56"
                id="slick-slide56"><button type="button" data-role="none" role="button" tabindex="0">7</button></li>
            <li aria-hidden="true" role="presentation" aria-selected="false" aria-controls="navigation57"
                id="slick-slide57"><button type="button" data-role="none" role="button" tabindex="0">8</button></li>
        </ul>
    </div>



	<jsp:include page="WEB-INF/views/common/footer.jsp"></jsp:include>
    <script>

    /*     $('.post-wrapper').slick({
            slidesToShow: 3,
            slidesToScroll: 1,
            autoplay: true,
            autoplaySpeed: 2000,
            nextArrow: $('.next'),
            prevArrow: $('.prev'),
        }) */
    </script>

</body>

</html>
	

	

	
