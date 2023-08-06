import Button from '@/components/atoms/Button/Button';
import { Text } from '@/components/atoms/Text/Text.styles';
import CalendarBar from '@/components/molecules/CalendarBar/CalendarBar';
import CategoryBar from '@/components/molecules/CategoryBar/CategoryBar';
import { PATH } from '@/constants/path';
import {
  CategoryBarContainer,
  GraphContainer,
  ToggleContainer,
} from '@/pages/GraphPage/GraphPage.style';
import theme from '@/styles/theme';
import { useEffect, useState } from 'react';
import { Outlet, useNavigate } from 'react-router-dom';

const GraphPage = () => {
  const navigate = useNavigate();

  useEffect(() => {
    const AllCategoryButton = document.getElementById('All');
    AllCategoryButton!.style.display = 'none';
  }, []);

  const childPath = [
    { name: '일별 그래프', path: PATH.GRAPHBYDAY, activate: true },
    { name: '주별 그래프', path: PATH.GRAPHBYWEEK, activate: false },
  ];

  const [graphType, setGraphType] = useState(childPath);

  const onClickGraphToggle = (path: string) => {
    graphType.map(childObj =>
      childObj.path === path
        ? (childObj.activate = true)
        : (childObj.activate = false)
    );
    navigate(path);
  };

  return (
    <>
      <section>
        <CalendarBar></CalendarBar>
        <CategoryBarContainer>
          <CategoryBar path={PATH.GRAPH}></CategoryBar>
        </CategoryBarContainer>
      </section>
      <GraphContainer>
        <ToggleContainer>
          {graphType.map(child => {
            return (
              <Button
                option={child.activate ? 'primary' : 'deActivated'}
                $border={!child.activate ? 'none' : undefined}
                $color={!child.activate && theme.color.gray1}
                size="medium"
                key={child.path}
                onClick={() => onClickGraphToggle(child.path)}
              >
                {child.name}
              </Button>
            );
          })}
        </ToggleContainer>

        <Outlet />
      </GraphContainer>
    </>
  );
};

export default GraphPage;
