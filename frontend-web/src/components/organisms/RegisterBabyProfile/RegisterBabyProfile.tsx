import GenderButtons from '@/components/molecules/GenderButtons/GenderButtons';
import { useState, ChangeEvent, useEffect } from 'react';
import Button from '@/components/atoms/Button/Button';
import Input from '@/components/atoms/Input/Input';
import theme from '@/styles/theme';
import { Value } from 'react-calendar/dist/cjs/shared/types';
import moment from 'moment';
import { CalendarModal } from '@/components/organisms/CalendarModal/CalendarModal';
import RBPWrapper from '@/components/organisms/RegisterBabyProfile/RegisterBabyProfile.style';
import { useNavigate } from 'react-router-dom';
import { PATH } from '@/constants/path';
import { Baby } from '@/types';
import { userInfoState } from '@/states/UserState';
import { useRecoilState } from 'recoil';

const RegisterBabyProfile = () => {
  const navigate = useNavigate();

  const placeholder = '이름을 입력하세요';

  const [value, setValue] = useState<string>('');
  const [gender, setGender] = useState<'male' | 'female'>('male');
  const [userInfo, setUserInfo] = useRecoilState(userInfoState); // userInfo.name을 가져다쓸것
  const [option, setOption] = useState<'deActivated' | 'activated'>(
    'deActivated'
  );

  useEffect(() => {
    if (value.length > 0) {
      setOption('activated');
    } else {
      setOption('deActivated');
    }
  }, [value]);

  const changeInput = (e: ChangeEvent<HTMLInputElement>) => {
    setValue(e.target.value);
  };

  const today = new Date();

  const [modalOpen, setModalOpen] = useState<boolean>(false);
  const [pickDate, setPickDate] = useState<Date>(today);
  const onClickAction = () => {
    setModalOpen(true);
  };

  const onCalendarChange = (value: Value) => {
    if (value instanceof Date) {
      // value값이 Date 객체의 인스턴스인지 검사
      setPickDate(value);
    }
  };

  const onModalClose = () => {
    setModalOpen(false);
  };

  const toMain = () => {
    if (option === 'activated') {
      const baby: Baby = {
        birthDate: moment(pickDate).format('YYYY-MM-DD'),
        gender,
        name: value,
        userId: userInfo.id, // userInfo는 recoil에!
      };
      // TODO: 이제 이걸 add(post) 해보자 !
      // TODO: RecoilState에도 저장해야함 !
      console.log(baby);
      navigate(PATH.MAIN);
    }
  };

  return (
    <RBPWrapper>
      {modalOpen && (
        <CalendarModal
          pickDate={pickDate}
          onModalClose={onModalClose}
          onCalendarChange={onCalendarChange}
        />
      )}
      <GenderButtons gender={gender} setGender={setGender} />
      <Button
        option="default"
        size="large"
        $borderRadius="14px"
        onClick={onClickAction}
      >
        {moment(pickDate).format('YYYY년 MM월 D일')}
      </Button>
      <Input
        onChange={changeInput}
        value={value}
        placeholder={placeholder}
        $borderRadius={14}
        fontSize={theme.fontSize.headSmall}
        height={3.5}
      />
      <Button
        option={option}
        size="medium"
        onClick={toMain}
        style={{ width: '16rem', height: '50px', borderRadius: '13px' }}
      >
        등록하기
      </Button>
    </RBPWrapper>
  );
};

export default RegisterBabyProfile;
