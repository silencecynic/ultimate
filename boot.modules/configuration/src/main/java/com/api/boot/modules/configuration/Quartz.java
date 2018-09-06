package com.api.boot.modules.configuration;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.*;

@Configuration
public class Quartz {

    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        AutoSpringBeanJobFactory jobFactory = new AutoSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }


    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(Job.class);
        jobDetailFactoryBean.setDescription("Invoke Sample Job Service .....");
        jobDetailFactoryBean.setDurability(true);
        return jobDetailFactoryBean;
    }


    @Bean
    @Qualifier
    public SchedulerFactoryBean schedulerFactoryBean(JobDetail job,final ApplicationContext applicationContext) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setSchedulerName("quartz scheduler");
        schedulerFactoryBean.setStartupDelay(10);
        schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContextSchedulerContextKey");
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        schedulerFactoryBean.setAutoStartup(true);
        schedulerFactoryBean.setJobFactory(jobFactory(applicationContext));
        schedulerFactoryBean.setJobDetails(job);
        return schedulerFactoryBean;
    }



    @Bean (name = "cronTrigger")
    public CronTriggerFactoryBean cronTriggerFactoryBean(JobDetail jobDetail) {
        CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
        stFactory.setJobDetail(jobDetail);
        stFactory.setStartDelay(0);
        stFactory.setCronExpression("* * * ? * *");
        return stFactory;
    }


    @Bean (name = "simpleTrigger")
    public SimpleTriggerFactoryBean simpleTriggerFactoryBean(JobDetail jobDetail) {
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetail);
        factoryBean.setStartDelay(0);
        factoryBean.setRepeatInterval(60000);
        factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT);
        return factoryBean;
    }


    @Bean
    public QuartzInitializerListener quartzInitializerListener() {
        return new QuartzInitializerListener();
    }


    @Bean
    public Scheduler scheduler(JobDetail job,final ApplicationContext applicationContext) {
        return schedulerFactoryBean(job, applicationContext).getScheduler();
    }


    private static final class AutoSpringBeanJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {
        private transient AutowireCapableBeanFactory capableBeanFactory;

        @Override
        public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
            capableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        }

        @Override
        protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {
            final Object jobInstance = super.createJobInstance(bundle);
            capableBeanFactory.autowireBean(jobInstance);
            return jobInstance;
        }
    }

}
