import styled from 'styled-components';
import theme from '@/styles/theme';

const DiaperPageContainer = styled.div`
  height: calc(100vh - 3rem);
  width: calc(100% - 20px);
  display: flex;
  /* align-items: center; */
  justify-content: center;
  background-color: white;
`;

const DiaperPageWrapper = styled.div`
  margin-top: 80px;
  padding: 0px 40px;
  width: 100vw;
  display: flex;
  flex-direction: column;
  /* justify-content: space-around; */
  align-items: center;
  text-align: center;
  font-family: 'Happiness-Sans';
`;

const Divider = styled.div`
  border-top: 1px solid ${theme.color.gray2};
  padding: 20px 0px;
  width: 70vw;
`;

export { DiaperPageContainer, DiaperPageWrapper, Divider };
