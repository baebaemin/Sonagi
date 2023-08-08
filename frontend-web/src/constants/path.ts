/* route 경로 지정 */
const PATH = {
  ROOT: '/',
  LOGIN: '/logIn',
  REDIRECT: '/oauth/redirect',
  SIGNIN: '/signIn',
  REGISTER: '/register',
  BABYCODE: '/babycode',
  VACCINATION: '/vaccination',
  MAIN: '/main',
  GRAPH: '/main/graph',
  GRAPHBYDAY: '/main/graph',
  GRAPHBYWEEK: '/main/graph/week',
  OURBABY: '/main/ourBaby',
  DIARY: '/main/diary',
  MYPAGE: '/main/myPage',
  REGISTERBABYPROFILE: '/regBabyProfile',
  DETAILRECORD: '/main/detailRecord',
} as const;

export { PATH };
