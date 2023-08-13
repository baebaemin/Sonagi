import Button from '@/components/atoms/Button/Button';
import { Text } from '@/components/atoms/Text/Text.styles';
import {
  AmountButtonWrapper,
  LeftWrapper,
  AmountRecorderContainer,
} from '@/components/molecules/AmountRecorder/AmountRecorder.style';
import { useState } from 'react';

interface AmountRecorderProps {
  unitType: string;
  unit: string;
  unitArray: number[];
  defaultValue: number;
  minValue: number;
  maxValue: number;
}

const AmountRecorder = ({
  unitType,
  unit,
  unitArray,
  defaultValue,
  minValue,
  maxValue,
}: AmountRecorderProps) => {
  const [value, setValue] = useState(defaultValue);

  const onAmountBtnClick = (changeAmount: number) => {
    const newValue = value + changeAmount;

    if (newValue >= minValue && newValue < maxValue) {
      // Use toFixed to round the value to the desired precision
      const roundedValue = parseFloat(newValue.toFixed(1));
      setValue(roundedValue);
    } else if (newValue < minValue) {
      setValue(minValue);
    } else {
      setValue(maxValue);
    }
  };

  return (
    <AmountRecorderContainer>
      <LeftWrapper>
        <Text size="headSmall">
          {unitType}, {unit}
        </Text>
      </LeftWrapper>
      <Text size="headLarge">
        {value}
        {unit}
      </Text>
      <AmountButtonWrapper>
        {unitArray.map((number, index) => (
          <Button
            style={{ padding: '0px, 10px' }}
            key={index}
            size="small"
            option="primary"
            $borderRadius="9px"
            onClick={() => {
              onAmountBtnClick(number);
            }}
          >
            <Text size="medium3">
              {number > 0 && '+'}
              {number}
            </Text>
          </Button>
        ))}
      </AmountButtonWrapper>
    </AmountRecorderContainer>
  );
};

export default AmountRecorder;
