import { ref } from 'vue';

export const useCityList = () => {
    const cities = ref([
        { title: 'Białystok', coords: [53.1325, 23.1688] },
        { title: 'Bydgoszcz', coords: [53.1235, 18.0084] },
        { title: 'Częstochowa', coords: [50.8118, 19.1203] },
        { title: 'Gdańsk', coords: [54.3520, 18.6466] },
        { title: 'Gdynia', coords: [54.5189, 18.5305] },
        { title: 'Katowice', coords: [50.2649, 19.0238] },
        { title: 'Kraków', coords: [50.0647, 19.9450] },
        { title: 'Lublin', coords: [51.2465, 22.5684] },
        { title: 'Łódź', coords: [51.7592, 19.4560] },
        { title: 'Poznań', coords: [52.4064, 16.9252] },
        { title: 'Radom', coords: [51.4027, 21.1471] },
        { title: 'Rzeszów', coords: [50.0413, 21.9990] },
        { title: 'Szczecin', coords: [53.4285, 14.5528] },
        { title: 'Warszawa', coords: [52.2297, 21.0122] },
        { title: 'Wrocław', coords: [51.1079, 17.0385] }
    ]);

    return { cities };
};
