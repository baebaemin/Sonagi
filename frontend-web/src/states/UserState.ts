import { atom } from 'recoil';
import { recoilPersist } from 'recoil-persist';

// localStorage 에 저장
const { persistAtom } = recoilPersist({
  key: 'UserState',
  storage: localStorage,
});

interface User {
  userId: number;
  name: string;
}

// 사용자 정보
export const userInfoState = atom<User>({
  key: 'userInfoState',
  default: {} as User,
  effects_UNSTABLE: [persistAtom],
});

// 로그인 여부
export const logInState = atom<boolean>({
  key: 'logInState',
  default: false,
  effects_UNSTABLE: [persistAtom],
});
