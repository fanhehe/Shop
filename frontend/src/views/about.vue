<template >
    <div id = "view-index-container" class = "main-wrap" :class = "device_type">
        <canvas id = "background-canvas" ref = "background-canvas"></canvas>
        <header class = "main-header"></header>
        <div class = "body">
            {{msg}}
        </div>
        <footer></footer>
    </div>
</template>

<script>

import { deviceDetect } from '@/utils/useragent';

export default {
    data () {
        return {
            msg: 'hehe',
            hanlder: null,
        };
    },
    methods: {
    },
    computed: {
        device_type () {
            const deviceType = deviceDetect() || 'client';
            return `full-screen-${deviceType}`;
        },
    },
    mounted () {
        const init = [];
        const maxParts = 1000;
        let w = window.innerWidth;
        let h = window.innerHeight;
        const canvas = this.$refs['background-canvas'];

        canvas.width = w;
        canvas.height = h;

        const ctx = canvas.getContext('2d');

        ctx.strokeStyle = 'rgba(174,194,224,0.5)';

        for (let a = 0; a < maxParts; a++) {
            init.push({
                x: Math.random() * w,
                y: Math.random() * h,
                l: Math.random() * 1,
                xs: -4 + Math.random() * 4 + 2,
                ys: Math.random() * 10 + 10,
            });
        }

        const particles = [];
        for (var b = 0; b < maxParts; b++) {
            particles[b] = init[b];
        }

        const draw = () => {
            ctx.clearRect(0, 0, w, h);
            for (var c = 0; c < particles.length; c++) {
                var p = particles[c];
                ctx.beginPath();
                ctx.moveTo(p.x, p.y);
                ctx.lineTo(p.x + p.l * p.xs, p.y + p.l * p.ys);
                ctx.stroke();
            }
            move();
            this.hanlder = requestAnimationFrame(draw);
        };

        const move = () => {
            for (var b = 0; b < particles.length; b++) {
                var p = particles[b];
                p.x += p.xs;
                p.y += p.ys;
                if (p.x > w || p.y > h) {
                    p.x = Math.random() * w;
                    p.y = -20;
                }
            }
        };

        this.hanlder = requestAnimationFrame(draw);

        window.addEventListener('resize', () => {
            canvas.width = w = window.innerWidth;
            canvas.height = h = window.innerHeight;
        });

        setTimeout(() => { cancelAnimationFrame(this.timer) }, 10);
    },
};
</script>

<style scoped>
    ::selection {
        background: transparent;
    }

    .main-wrap {
        position: relative;
        background: #212737 repeat;
    }
</style>
