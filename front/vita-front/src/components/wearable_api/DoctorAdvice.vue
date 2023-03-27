<template>
    <div id="AdviceContainer">
        <div id="AdviceSpeechBubble">
            <img id="img-doctor" src="@/assets/Doctor.png">
            <div id="advice">{{ Advice }}</div>
        </div>
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
        padding: 9% 10% 9% 4%;
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
        font-weight: 600;
        color: #172176;
    }
</style>