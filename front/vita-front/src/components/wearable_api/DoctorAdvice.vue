<template>
    <div id="AdviceContainer">
        <!-- <div id="AdviceSpeechBubble"> -->
            <img id="img-doctor" src="@/assets/Doctor.png">
            <div id="advice">{{ Advice }}</div>
        <!-- </div> -->
    </div>
</template>
<script>
export default {
    name: 'DoctorAdvice',
    props: {
        Advice: String,
    }
}
</script>
<style scoped>
    #AdviceContainer {
        background-image: url("@/assets/SpeechBubble.png");
        background-repeat : no-repeat;
        background-size : cover;
        max-height: 100%;
        max-width: 100%;
        padding: 30px;
        display: flex;
        justify-content : center;
    }
    #img-doctor {
        width: 50px;
        height: 50px;
        float: left;
    }
    #advice {
        text-align: center;
        font-weight: 800;
        color: #172176;
    }
</style>