<template>
    <div id = "HelloWorldContainer">
        {{ msg }}
    </div>
</template>

<script>
export default {
    data () {
        return {
            msg: 'Welcome to Your Vue.js App'
        };
    }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
