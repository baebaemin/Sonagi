import { CardContentBar } from '@/components/molecules/CardContent/CardContentBar';
import { CardContentText } from '@/components/molecules/CardContent/CardContentText';
import { CardHeader } from '@/components/molecules/CardHeader/CardHeader';
import { CardContainer } from '@/components/organisms/Card/Card.styles';
import { categoryToColorMap } from '@/constants/categoryToColorMap';
import theme from '@/styles/theme';

interface HealthCardProps {
  data: {
    feverAvg: number;
    feverAvgPercent: number;
    yesterdayFeverAvgPercent: number;
    hospitalCnt: number;
    hospitalCntPercent: number;
    yesterdayHospitalCntPercent: number;
    medicationCnt: number;
    medicationCntPercent: number;
    yesterdayMedicationCntPercent: number;
  };
}

const HealthCard = ({ data }: HealthCardProps) => {
  const currentTheme = categoryToColorMap['Health'];
  const color = theme.color[currentTheme];

  return (
    <CardContainer $borderColor={color}>
      <CardHeader
        text="건강 통계"
        imgSrc={require(`@/assets/images/img-health.png`)}
      />
      <CardContentText type="체온" data={data.feverAvg} unit="도" />
      <CardContentBar
        $borderColor={color}
        $ratio={data.feverAvgPercent}
        $yesterDayRatio={data.yesterdayFeverAvgPercent}
      />

      <CardContentText type="병원 진료" data={data.hospitalCnt} unit="회" />
      <CardContentBar
        $borderColor={color}
        $ratio={data.hospitalCntPercent}
        $yesterDayRatio={data.yesterdayHospitalCntPercent}
      />

      <CardContentText type="투약" data={data.medicationCnt} unit="회" />
      <CardContentBar
        $borderColor={color}
        $ratio={data.medicationCntPercent}
        $yesterDayRatio={data.yesterdayMedicationCntPercent}
      />
    </CardContainer>
  );
};

export { HealthCard };
