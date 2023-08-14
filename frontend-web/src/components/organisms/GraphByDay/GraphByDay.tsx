import { GrapByDayContainer } from '@/components/organisms/GraphByDay/GraphByDay.styles';
// import { Card } from '@/components/organisms/Card/Card';
import DoughnutChart from '@/components/molecules/DoughnutChart/DoughnutChart';
import { useGetMealStatistics } from '@/apis/Statistic/Queries/useGetMealStatistics';
import { useRecoilValue } from 'recoil';
import { selectedBabyState } from '@/states/babyState';
import { BabiesOfUser, Category } from '@/types';
import { selectedDateState } from '@/states/dateState';
import { selectedCategoryState } from '@/states/categoryState';
import { PATH } from '@/constants/path';
// import { MealCard } from '@/components/organisms/Card/MealCard';
import { useGetDiaperStatistics } from '@/apis/Statistic/Queries/useGetDiaperStatistics';
import { DiaperCard } from '@/components/organisms/Card/DiaperCard';
import { SleepCard } from '@/components/organisms/Card/SleepCard';
import { useGetSleepStatistics } from '@/apis/Statistic/Queries/useGetSleepStatistics';
import { useGetPumpStatistics } from '@/apis/Statistic/Queries/useGetPumpStatistics';
import { useGetActivityStatistics } from '@/apis/Statistic/Queries/useGetActivityStatistics';
import { useGetHealthStatistics } from '@/apis/Statistic/Queries/useGetHealthStatistics';
import { useGetExtraStatistics } from '@/apis/Statistic/Queries/useGetExtraStatistics';
import { PumpCard } from '@/components/organisms/Card/PumpCard';
import { ActivityCard } from '@/components/organisms/Card/ActivityCard';
import { HealthCard } from '@/components/organisms/Card/HealthCard';
import { ExtraCard } from '@/components/organisms/Card/ExtraCard';
import React, { Suspense, lazy } from 'react';
import LoadingPage from '@/pages/LoadingPage/LoadingPage';
import { UseQueryResult, useQueries } from '@tanstack/react-query';
import { queries } from '@testing-library/react';
import { useGetAllStatistics } from '@/apis/Statistic/Queries/useGetAllStatistics';

const mealCardPromise = import('@/components/organisms/Card/MealCard');

const MealCard = React.lazy(() =>
  mealCardPromise.then(({ MealCard }) => ({ default: MealCard }))
);

const GraphByDay = () => {
  const currentCategory: Category = useRecoilValue(
    selectedCategoryState(PATH.GRAPH)
  );
  const babyInfo: BabiesOfUser = useRecoilValue(selectedBabyState);
  const date = useRecoilValue(selectedDateState);

  const getGraphDatas = useGetAllStatistics(babyInfo.babyId, 'day', date);
  console.log(getGraphDatas[2].data.times);
  return (
    <Suspense fallback={<LoadingPage />}>
      <GrapByDayContainer className="scrollable">
        <DoughnutChart />
        {currentCategory === 'Meal' && (
          <MealCard data={getGraphDatas[0].data} />
        )}
        {currentCategory === 'Diaper' && (
          <DiaperCard data={getGraphDatas[1].data} />
        )}
        {currentCategory === 'Sleep' && (
          <SleepCard data={getGraphDatas[2].data} />
        )}
        {currentCategory === 'Pump' && (
          <PumpCard data={getGraphDatas[3].data} />
        )}
        {currentCategory === 'Activity' && (
          <ActivityCard data={getGraphDatas[4].data} />
        )}
        {currentCategory === 'Health' && (
          <HealthCard data={getGraphDatas[5].data} />
        )}
        {currentCategory === 'Extra' && (
          <ExtraCard data={getGraphDatas[6].data} />
        )}
      </GrapByDayContainer>
    </Suspense>
  );
};

export { GraphByDay };
