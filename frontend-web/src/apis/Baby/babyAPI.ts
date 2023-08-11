import { instance } from '@/apis/instance';
import { Baby } from '@/types';

const addBaby = async (baby: Baby) => {
  try {
    await instance.post('/babyInfos', baby);
  } catch {
    new Error('baby info add error');
  }
};

const getBaby = async (userId: number) => {
  try {
    const response = await instance.get(`/babyInfos/${userId}`);
    console.log('getBaby의 data', response.data);
    return response.data;
  } catch {
    new Error('No data returned from the API');
  }
};

const getCoParent = async (userId: number, babyId: number) => {
  try {
    const response = await instance.get(`/coparents/${userId}/${babyId}`);
    return response.data;
  } catch {
    new Error('get coparent list error');
  }
};

const getBabyCode = async (babyId: number) => {
  try {
    const response = await instance.get(`/babyCode?babyId=${babyId}`);
    return response.data;
  } catch {
    new Error('get baby code error');
  }
};

export { addBaby, getBaby, getCoParent, getBabyCode };
