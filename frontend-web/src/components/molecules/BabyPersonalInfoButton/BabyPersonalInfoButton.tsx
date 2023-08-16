import Button from '@/components/atoms/Button/Button';
import { Text } from '@/components/atoms/Text/Text.styles';
import * as S from '@/components/molecules/BabyPersonalInfoButton/BabyPersonalInfoButton.styles';
import { CautionModal } from '@/components/organisms/CautionModal/CautionModal';
import { useState } from 'react';

interface BabyPersonalInfoButtonProps {
  memo: string;
  name: string;
  isDisease: boolean;
}

const BabyPersonalInfoButton = ({
  memo,
  name,
  isDisease,
}: BabyPersonalInfoButtonProps) => {
  const [cautionModalOpen, setCautionModalOpen] = useState(false);

  const modalClose = (
    setState: React.Dispatch<React.SetStateAction<boolean>>
  ) => {
    setState(false);
  };

  return (
    <>
      <CautionModal
        name={name}
        memo={memo}
        onModalClose={() => modalClose(setCautionModalOpen)}
        modalOpen={cautionModalOpen}
        isDisease={isDisease}
      />
      <S.BabyPersonalInfoButtonContainer
        onClick={() => {
          setCautionModalOpen(true);
        }}
      >
        <Button>
          <Text style={{ height: '100px' }} size="medium3">
            {memo}
          </Text>
          <Text style={{ marginTop: '5px', float: 'right' }} size="small">
            {name}
          </Text>
        </Button>
      </S.BabyPersonalInfoButtonContainer>
    </>
  );
};

export { BabyPersonalInfoButton };
