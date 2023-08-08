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

const RegisterBabyProfile = () => {
  const navigate = useNavigate();

  const placeholder = '이름을 입력하세요';

  const [value, setValue] = useState<string>('');
  const [gender, setGender] = useState<'male' | 'female'>('male');

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
  // TODO: 아기 추가시 오늘 날짜보다 늦은 날짜면 선택할 수 없게 하기
  const onClickAction = () => {
    setModalOpen(true);
  };

  const onCalendarChange = (value: Value) => {
    if (value instanceof Date) {
      setPickDate(value);
    }
  };

  const onModalClose = () => {
    setModalOpen(false);
  };

  const toMain = () => {
    if (option === 'activated') {
      // TODO: 아기 한 명 추가
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
